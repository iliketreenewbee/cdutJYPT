package com.example.demo.controller;

import com.example.demo.bean.Order;
import com.example.demo.bean.Products;
import com.example.demo.bean.Shopping_car;
import com.example.demo.reqParam.OrderInfo;
import com.example.demo.result.JSONResponse;
import com.example.demo.result.ResultData;
import com.example.demo.service.OrderServ;
import com.example.demo.service.ProductsServ;
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
@RequestMapping("/Order")
public class OrderCtrl {
    @Autowired
    @Qualifier("OrderService")
    private OrderServ orderServ;
    @Autowired
    @Qualifier("ProductService")
    private ProductsServ productsServ;

    @RequestMapping("/Insert")
    public void insert(@RequestBody OrderInfo orderInfo, HttpServletResponse response) throws IOException {
//        String str = (String) map.get("order_list");
//        System.out.println(str);
        List<Order> order_list = orderInfo.getOrder_list();
        System.out.println("订单数量为："+order_list.size());
        System.out.println("总价1："+order_list.get(0).getAllPrice());
        //System.out.println(map.get());
        List<Shopping_car> shopC_list = orderInfo.getShopC_list();
        JSONResponse jsonResponse = new JSONResponse(response);
        //检查商品数量是否充足,不足则返回退出
        List<Products> not_enough_list = productsServ.SelectNotEnough(shopC_list);
        if(!not_enough_list.isEmpty()){
            jsonResponse.result = ResultData.not_enough();
            jsonResponse.JSONWrite();
            return;
        }
        int flag = orderServ.Insert(order_list,shopC_list);
        jsonResponse.DBresult(flag);
    }

    @RequestMapping("/Delete")
    public void delete(@RequestParam(value = "id") int id,HttpServletResponse response) throws IOException {
        JSONResponse jsonResponse = new JSONResponse(response);
        int flag = orderServ.Delete(id);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/LogicDelete")
    public void logicDelete(@RequestParam(value = "id") int id,HttpServletResponse response) throws IOException {
        JSONResponse jsonResponse = new JSONResponse(response);
        int flag = orderServ.LogicDelete(id);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/SelectAll")
    public void selectAll(HttpServletResponse response) throws IOException {
        List<Order> order_list = orderServ.SelectAll();
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresultByList(order_list);
    }
    @RequestMapping("/SelectByUser")
    public void selectByUser(@RequestParam(value = "id")int id, HttpServletResponse response) throws IOException {
        List<Order> order_list = orderServ.SelectByUserId(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresultByList(order_list);
    }
    @RequestMapping("/SelectByOwner")
    public void selectByOwner(@RequestParam(value = "id")int id, HttpServletResponse response) throws IOException {
        List<Order> order_list = orderServ.SelectByOwnerId(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresultByList(order_list);
    }
    @RequestMapping("/Update")
    public void update(@RequestBody Order order, HttpServletResponse response) throws IOException {
        int flag = orderServ.UpdateSatus(order);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
}
