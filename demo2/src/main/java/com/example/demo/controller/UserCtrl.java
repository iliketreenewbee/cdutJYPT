package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.jwt.JwtHelper;
import com.example.demo.result.JSONResponse;
import com.example.demo.result.ResultData;
import com.example.demo.service.UserServ;
import com.example.demo.tool.imgTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/User")
public class UserCtrl {
    @Autowired
    @Qualifier("UserService")
    private UserServ userServ;
    @Autowired
    private JwtHelper jwtHelper;

    @RequestMapping("/Login")
    public void login(@RequestBody Map<String,Object> map,
                      HttpServletResponse response) throws IOException {
        String phone = (String)map.get("phone");
        String password = (String)map.get("password");
        System.out.println("账号是"+phone);
        System.out.println("密码是"+password);
        List<User> user_list = userServ.LoginByPhone(phone);
        ResultData result;
        JSONResponse jsonResponse = new JSONResponse(response);
        if(user_list.size()==0){
            result = ResultData.error_uername();
            jsonResponse.setResult(result);
            jsonResponse.JSONWrite();
            return;
        }
        for (int i=0;i<user_list.size();i++){
            String pwd = user_list.get(i).getPassword();
            if(pwd.equals(password)){
                User user = user_list.get(i);
                user.setPassword("");
                String token = jwtHelper.generateToken(phone, password);
                result = ResultData.ok();
                result.addData("user",user);
                result.addData("token",token);
                jsonResponse.setResult(result);
                jsonResponse.JSONWrite();
                return;
            }
        }
        result = ResultData.error_password();
        jsonResponse.setResult(result);
        jsonResponse.JSONWrite();
        return;
    }

    @RequestMapping("/Insert")
    public void insert(@RequestParam(value="file",required=false)MultipartFile file, @RequestBody User user, HttpServletResponse response) throws IOException {
        ResultData result;
        System.out.println("开始执行Use/Insert");
        JSONResponse jsonResponse = new JSONResponse(response);
        if(user==null){
            jsonResponse.DBresult(0);
            System.out.println("收到的参数为空");
            return;
        }
        System.out.println("开始保存图片");
        if (!file.isEmpty()){
            imgTool imgtool = new imgTool();
            String imgName = "User"+user.getId();
            String imgPath = imgtool.savaImg(file,imgName);
            if (!imgPath.isEmpty()){
                user.setImg(imgPath);
                System.out.println("保存成功");
            }
        }
        System.out.println("开始插入数据库");
        List<User> user_list = userServ.LoginByPhone(user.getPhone());
        if (user_list.size()!=0){
            result = ResultData.repeat();
            jsonResponse.setResult(result);
            jsonResponse.JSONWrite();
            return;
        }
        int flag = userServ.Insert(user);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/Delete")
    public void delete(@RequestParam(value = "id")int id,HttpServletResponse response) throws IOException {
        int flag = userServ.Delete(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
        return;
    }
    @RequestMapping("/Ban")
    public void ban(@RequestParam(value = "id")int id,HttpServletResponse response) throws IOException {
        int flag = userServ.BanUser(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
        return;
    }
    @RequestMapping("/Update")
    public void update(@RequestParam(value="file",required=false)MultipartFile file,User user,HttpServletResponse response) throws IOException {
        System.out.println("开始保存图片");
        if (!file.isEmpty()){
            imgTool imgtool = new imgTool();
            String imgName = "User"+user.getId();
            String imgPath = imgtool.savaImg(file,imgName);
            if (!imgPath.isEmpty()){
                user.setImg(imgPath);
                System.out.println("保存成功");
            }
        }
        int flag = userServ.UpdateInfo(user);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
        return;
    }
    @RequestMapping("/SelectAll")
    public void selectAll(HttpServletResponse response) throws IOException {
        System.out.println("User/SelectAll被访问");
        List<User> user_list = userServ.SelectAll();
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresultByList(user_list);
    }
}
