package com.example.demo.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JSONResponse {
    public HttpServletResponse response;
    public ResultData result = new ResultData();

    public JSONResponse(){

    }
    public JSONResponse(HttpServletResponse response){
        this.response = response;
    }
    public JSONResponse(HttpServletResponse response,ResultData result){
        this.response = response;
        this.result = result;
    }
    public void JSONWrite() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(result);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(jsonStr);
    }
    public void DBresult(int flag) throws IOException {
        if (flag != 0){
            this.result = ResultData.ok();
        }else{
            this.result = ResultData.db_error();
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(this.result);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(jsonStr);
        return;
    }
    public <T extends List> void DBresultByList(T result_list) throws IOException {
        if(result_list.isEmpty()){
            this.result = ResultData.db_error();
        }else{
            this.result = ResultData.ok();
            this.result.addData("result_list",result_list);
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(this.result);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(jsonStr);
        return;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public ResultData getResult() {
        return result;
    }

    public void setResult(ResultData result) {
        this.result = result;
    }
}
