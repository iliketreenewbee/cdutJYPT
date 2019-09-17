package com.example.demo.controller;

import com.example.demo.bean.Products;
import com.example.demo.result.JSONResponse;
import com.example.demo.result.ResultData;
import com.example.demo.service.ProductsServ;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("/Product")
public class ProductCtrl {
    @Autowired
    @Qualifier("ProductService")
    private ProductsServ productsServ;

    @RequestMapping("/SelectAll")
    public void SelectAll(HttpServletResponse response) throws IOException {
        List<Products> products_list = productsServ.SelectAll();
        ResultData result;
        if(products_list.isEmpty()==true){
            result = ResultData.db_error();
        }else{
            result = ResultData.ok();
            result.addData("products_list",products_list);
        }
        JSONResponse jsonResponse = new JSONResponse(response,result);
        jsonResponse.JSONWrite();
    }
    @RequestMapping("/SelectByOwner")
    public void selectByOwner(HttpServletResponse response, @RequestParam("id") int id) throws IOException {
        List<Products> products_list = productsServ.SelectByShopOwner(id);
        ResultData result;
        if(products_list.isEmpty()==true){
            result = ResultData.notFound();
        }else{
            result = ResultData.ok();
            result.addData("products_list",products_list);
        }
        JSONResponse jsonResponse = new JSONResponse(response,result);
        jsonResponse.JSONWrite();
    }
    @RequestMapping("/SelectByKey")
    public void selectByKey(HttpServletResponse response, @RequestParam("key") String key) throws IOException {
        List<Products> products_list = productsServ.SelectWtithKey(key);
        ResultData result;
        if(products_list.isEmpty()==true){
            result = ResultData.notFound();
        }else{
            result = ResultData.ok();
            result.addData("products_list",products_list);
        }
        JSONResponse jsonResponse = new JSONResponse(response,result);
        jsonResponse.JSONWrite();
    }
    @RequestMapping("/Insert")
    public void insert(@RequestBody Products products,HttpServletResponse response) throws IOException {
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
    public void update(@RequestBody Products products,HttpServletResponse response) throws IOException {
        int flag = productsServ.Update(products);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
}
