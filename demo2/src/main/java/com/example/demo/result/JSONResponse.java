package com.example.demo.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JSONResponse {
    private HttpServletResponse response;
    private ResultData result;

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
        if (flag == 1){
            this.result = ResultData.ok();
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(result);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(jsonStr);
            return;
        }else{
            this.result = ResultData.db_error();
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(result);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(jsonStr);
            return;
        }
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
