package com.example.demo.result;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
    private int code;           //状态码
    private String message;     //状态信息
    private Map<String ,Object> data = new HashMap<>();        //返回数据
    public ResultData(){

    };
    public ResultData(int code,String message){
        this.code = code;
        this.message = message;
    }
    public ResultData(int code,String message,Map<String ,Object> data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public void addData(String key,Object value){
        this.data.put(key,value);
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Map<String ,Object> data) {
        this.data = data;
    }
    //数据库内容已存在
    public static ResultData repeat() {
        return new ResultData(
                ResultStatus.REPEAT.getCode(),
                ResultStatus.REPEAT.getMessage());
    }
    //用户名错误
    public static ResultData error_uername() {
        return new ResultData(
                ResultStatus.ERROR_USERNAME.getCode(),
                ResultStatus.ERROR_USERNAME.getMessage());
    }
    //密码错误
    public static ResultData error_password() {
        return new ResultData(
                ResultStatus.ERROR_PASSWORD.getCode(),
                ResultStatus.ERROR_PASSWORD.getMessage());
    }
    //成功
    public static ResultData ok(){
        return new ResultData(
                ResultStatus.SUCCESS.getCode(),
                ResultStatus.SUCCESS.getMessage());
    }
    //没有找到
    public static ResultData notFound() {
        return new ResultData(
                ResultStatus.NOT_FOND.getCode(),
                ResultStatus.NOT_FOND.getMessage());
    }
    //错误的请求
    public static ResultData errorRequest() {
        return new ResultData(
                ResultStatus.ERROR_REQUEST.getCode(),
                ResultStatus.ERROR_REQUEST.getMessage());
    }
    //没有权限访问
    public static ResultData forbidden() {
        return new ResultData(
                ResultStatus.FORBIDDEN.getCode(),
                ResultStatus.FORBIDDEN.getMessage());
    }
    //没有授权
    public static ResultData unauthorized() {
        return new ResultData(
                ResultStatus.UNAUTHORIZED.getCode(),
                ResultStatus.UNAUTHORIZED.getMessage());
    }
    //服务器错误
    public static ResultData serverError() {
        return new ResultData(
                ResultStatus.SERVER_ERROR.getCode(),
                ResultStatus.SERVER_ERROR.getMessage());
    }
    //数据库操作失败
    public static ResultData db_error() {
        return new ResultData(
                ResultStatus.DATABASE_ERROR.getCode(),
                ResultStatus.DATABASE_ERROR.getMessage());
    }
    //商品数量不足
    public static ResultData not_enough() {
        return new ResultData(
                ResultStatus.NOT_ENOUGH.getCode(),
                ResultStatus.NOT_ENOUGH.getMessage());
    }
    //未知错误
    public static ResultData error() {
        return new ResultData(
                ResultStatus.ERROR.getCode(),
                ResultStatus.ERROR.getMessage());
    }
}
