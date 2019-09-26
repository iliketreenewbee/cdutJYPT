package com.example.demo.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JwtFilter implements javax.servlet.Filter {
    private JwtHelper jwtHelper;
    private List<String> urls = null;
    private static final org.springframework.util.PathMatcher pathMatcher = new AntPathMatcher();

    public JwtFilter(JwtHelper jwtHelper, String[] authorisedUrls) {
        this.jwtHelper = jwtHelper;
        urls = Arrays.asList(authorisedUrls);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        if ("OPTIONS".equals(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value()); // HttpStatus.SC_NO_CONTENT = 204
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Token");
            httpResponse.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
        }
        String spath = httpRequest.getServletPath();
        System.out.println("当前访问路径"+spath);
        try {
            // 验证受保护的接口
            for (String url : urls) {
                System.out.println("授权路径"+url);
                if (pathMatcher.match(url, spath)) {
                    Map<String,Object> token = jwtHelper.validateTokenAndGetClaims(httpRequest);
                    if (token != null) {
                        System.out.println("结果：");
                        System.out.println("token解析结果："+token.toString());
                        chain.doFilter(request, response);
                        return;
                    }else{
                        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未授权或者授权已经过期");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response);
        return;
    }
}
