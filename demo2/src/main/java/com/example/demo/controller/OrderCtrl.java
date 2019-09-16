package com.example.demo.controller;

import com.example.demo.bean.Order;
import com.example.demo.bean.Products;
import com.example.demo.bean.Shopping_car;
import com.example.demo.result.JSONResponse;
import com.example.demo.result.ResultData;
import com.example.demo.service.OrderServ;
import com.example.demo.service.ProductsServ;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void insert(@RequestParam(value = "orderInfo") String orderInfo,
                       @RequestParam(value = "productInfo") String productInfo,
                       HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.readValue(orderInfo,Order.class);
        JavaType javaType =  mapper.getTypeFactory().constructParametricType(ArrayList.class, Shopping_car.class);
        List<Shopping_car> shopC_list = mapper.readValue(productInfo,javaType);
        JSONResponse jsonResponse = new JSONResponse(response);
        //检查商品数量是否充足,不足则返回退出
        List<Products> not_enough_list = productsServ.SelectNotEnough(shopC_list);
        if(!not_enough_list.isEmpty()){
            ResultData result = ResultData.not_enough();
            jsonResponse.setResult(result);
            jsonResponse.JSONWrite();
            return;
        }
        int flag = orderServ.Insert(order,shopC_list);
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
        ResultData result;
        if (order_list.isEmpty()){
            result = ResultData.db_error();
        }else{
            result = ResultData.ok();
            result.setData(order_list);
        }
        JSONResponse jsonResponse = new JSONResponse(response,result);
        jsonResponse.JSONWrite();
    }
    @RequestMapping("/SelectByUser")
    public void selectByUser(@RequestParam(value = "id")int id, HttpServletResponse response) throws IOException {
        List<Order> order_list = orderServ.SelectByUserId(id);
        ResultData result;
        if (order_list.isEmpty()){
            result = ResultData.db_error();
        }else{
            result = ResultData.ok();
            result.setData(order_list);
        }
        JSONResponse jsonResponse = new JSONResponse(response,result);
        jsonResponse.JSONWrite();
    }
    @RequestMapping("/SelectByOwner")
    public void selectByOwner(@RequestParam(value = "id")int id, HttpServletResponse response) throws IOException {
        List<Order> order_list = orderServ.SelectByOwnerId(id);
        ResultData result;
        if (order_list.isEmpty()){
            result = ResultData.db_error();
        }else{
            result = ResultData.ok();
            result.setData(order_list);
        }
        JSONResponse jsonResponse = new JSONResponse(response,result);
        jsonResponse.JSONWrite();
    }
    @RequestMapping("/Update")
    public void update(@RequestBody Order order, HttpServletResponse response) throws IOException {
        int flag = orderServ.UpdateSatus(order);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
}
