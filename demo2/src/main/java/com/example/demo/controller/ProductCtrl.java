package com.example.demo.controller;

import com.example.demo.bean.Products;
import com.example.demo.result.JSONResponse;
import com.example.demo.result.ResultData;
import com.example.demo.service.ProductsServ;
import com.example.demo.tool.imgTool;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("/Product")
public class ProductCtrl {
    @Autowired
    @Qualifier("ProductService")
    private ProductsServ productsServ;

    @RequestMapping("/SelectAll")
    public void SelectAll(HttpServletResponse response) throws IOException {
        List<Products> products_list = productsServ.SelectAll();
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresultByList(products_list);
    }
    @RequestMapping("/SelectById")
    public void selectById(HttpServletResponse response, @RequestParam("id") int id) throws IOException {
        ResultData result;
        JSONResponse jsonResponse = new JSONResponse(response);
        System.out.println("当前id："+id);
        Products product = productsServ.SelectById(id);
        if (product!=null){
            result = ResultData.ok();
            result.addData("product",product);
            jsonResponse.setResult(result);
            jsonResponse.JSONWrite();
        }
        result = ResultData.db_error();
        jsonResponse.setResult(result);
        jsonResponse.JSONWrite();
        return;
    }
    @RequestMapping("/SelectByOwner")
    public void selectByOwner(HttpServletResponse response, @RequestParam("OwnerId") int OwnerId) throws IOException {
        System.out.println("当前OwnerId："+OwnerId);
        List<Products> products_list = productsServ.SelectByShopOwner(OwnerId);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresultByList(products_list);
    }
    @RequestMapping("/SelectByKey")
    public void selectByKey(HttpServletResponse response, @RequestParam("key") String key) throws IOException {
        List<Products> products_list = productsServ.SelectWtithKey(key);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresultByList(products_list);
    }
    @RequestMapping("/Insert")
    public void insert(@RequestParam(value="file",required=false)MultipartFile file,
                       @RequestBody Products products, HttpServletResponse response) throws IOException {
        System.out.println("开始保存图片");
        if (!file.isEmpty()){
            imgTool imgtool = new imgTool();
            String imgName = "Product"+products.getId();
            String imgPath = imgtool.savaImg(file,imgName);
            if (!imgPath.isEmpty()){
                products.setImgPath(imgPath);
                System.out.println("保存成功");
            }
        }
        int flag = productsServ.Insert(products);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/LogicInsert")
    public void logicInsert( int id,HttpServletResponse response) throws IOException {
        int flag = productsServ.LogicInsert(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/Delete")
    public void delete( int id,HttpServletResponse response) throws IOException {
        int flag = productsServ.Delete(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/LogicDelete")
    public void logicDelete( int id,HttpServletResponse response) throws IOException {
        int flag = productsServ.LogicDelete(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/Update")
    public void update(@RequestParam(value="file",required=false)MultipartFile file,
                       @RequestBody Products products,HttpServletResponse response) throws IOException {
        System.out.println("开始保存图片");
        if (!file.isEmpty()){
            imgTool imgtool = new imgTool();
            String imgName = "Product"+products.getId();
            String imgPath = imgtool.savaImg(file,imgName);
            if (!imgPath.isEmpty()){
                products.setImgPath(imgPath);
                System.out.println("保存成功");
            }
        }
        int flag = productsServ.Update(products);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
}
