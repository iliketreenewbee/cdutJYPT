package com.example.demo.controller;

import com.example.demo.bean.Good_items;
import com.example.demo.result.JSONResponse;
import com.example.demo.result.ResultData;
import com.example.demo.service.Good_itemsServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/GoodType")
public class Good_itemsCtrl {
    @Autowired
    @Qualifier("GooditemService")
    private Good_itemsServ goodItemsServ;

    @RequestMapping("/SelectAll")
    public void selectAll(HttpServletResponse response) throws IOException {
        List<Good_items> type_list = goodItemsServ.SelectAll();
        ResultData result;
        if (type_list.isEmpty()){
            result = ResultData.db_error();
        }else{
            result = ResultData.ok();
            result.addData("type_list",type_list);
        }
        JSONResponse jsonResponse = new JSONResponse(response,result);
        jsonResponse.JSONWrite();
    }
    @RequestMapping("/SelectIsVal")
    public void selectIsVal(HttpServletResponse response) throws IOException {
        List<Good_items> type_list = goodItemsServ.SelectIsVal();
        ResultData result;
        if (type_list.isEmpty()){
            result = ResultData.db_error();
        }else{
            result = ResultData.ok();
            result.addData("type_list",type_list);
        }
        JSONResponse jsonResponse = new JSONResponse(response,result);
        jsonResponse.JSONWrite();
    }
    @RequestMapping("/SelectByKey")
    public void selectByKey(String key,HttpServletResponse response) throws IOException {
        List<Good_items> type_list = goodItemsServ.SelectWithKey(key);
        ResultData result;
        if (type_list.isEmpty()){
            result = ResultData.db_error();
        }else{
            result = ResultData.ok();
            result.addData("type_list",type_list);
        }
        JSONResponse jsonResponse = new JSONResponse(response,result);
        jsonResponse.JSONWrite();
    }
    @RequestMapping("/Insert")
    public void insert(Good_items good_items,HttpServletResponse response) throws IOException {
        int flag = goodItemsServ.Insert(good_items);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/Delete")
    public void delete(@RequestParam(value = "id")int id, HttpServletResponse response) throws IOException {
        int flag = goodItemsServ.Delete(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/LogicDelete")
    public void logicDelete(@RequestParam(value = "id")int id, HttpServletResponse response) throws IOException {
        int flag = goodItemsServ.LogicDelete(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
    @RequestMapping("/LogicInsert")
    public void logicInsert(@RequestParam(value = "id")int id, HttpServletResponse response) throws IOException {
        int flag = goodItemsServ.LogicInsert(id);
        JSONResponse jsonResponse = new JSONResponse(response);
        jsonResponse.DBresult(flag);
    }
}
