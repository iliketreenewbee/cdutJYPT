package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/User")
public class UserCtrl {
    @Autowired
    @Qualifier("UserService")
    private UserServ userServ;

    @RequestMapping("/Login")
    public void login(@RequestParam(value = "phone") int phone,
                      @RequestParam(value = "password") String password,
                      HttpServletResponse response){
        List<User> user_list = userServ.LoginByPhone(phone);
    }
}
