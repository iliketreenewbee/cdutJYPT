package com.example.demo.controller;

import com.example.demo.bean.Shopping_car;
import com.example.demo.result.JSONResponse;
import com.example.demo.result.ResultData;
import com.example.demo.service.ShopCServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/UserProduct")
public class ShopCCtrl {
    @Autowired
    @Qualifier("ShopCService")
    private ShopCServ shopCServ;

    @RequestMapping("/Insert")
    public void insert(@RequestBody Shopping_car shopping_car, HttpServletResponse response) throws IOException {
        System.out.println("当前名称"+shopping_car.getProductName());
        int flag = shopCServ.InsertGood(shopping_car);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/Delete")
    public void insert(@RequestParam(value = "id") int id, HttpServletResponse response) throws IOException {
        int flag = shopCServ.Delete(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/SelectByShopC")
    public void selectByShopc(@RequestParam(value = "UserId")int UserId,HttpServletResponse response) throws IOException {
        List<Shopping_car> shopC_list = shopCServ.SelectShopC(UserId);
        System.out.println("当前id"+UserId);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresultByList(shopC_list);
    }
    @RequestMapping("/SelectByOrder")
    public void selectByOrder(@RequestParam(value = "OrderId")int OrderId,HttpServletResponse response) throws IOException {
        List<Shopping_car> order_product_list = shopCServ.SelectOrder(OrderId);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresultByList(order_product_list);
    }
}
