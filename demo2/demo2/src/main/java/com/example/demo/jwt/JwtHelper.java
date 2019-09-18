package com.example.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtHelper {
    private long EXPIRATION_TIME;
    private String SECRET;
    private final String HEADER_STRING = "Authorization";

    public JwtHelper(String secret, long expire) {
        this.EXPIRATION_TIME = expire;
        this.SECRET = secret;
        System.out.println("正在初始化Jwthelper，expire="+expire);
    }

    public String generateToken(String phone,String password){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, (int)EXPIRATION_TIME);
        Date d = c.getTime();
        Claims claims = Jwts.claims();
        claims.put("phone",phone);
        claims.put("password",password);
        claims.setIssuer("cdutJYPT");
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(d)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }
    public Map<String, Object> validateTokenAndGetClaims(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        System.out.println("token is:"+token);
        if (token == null) {
            return null;
        }
        Map<String, Object> body = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return body;
    }
}
