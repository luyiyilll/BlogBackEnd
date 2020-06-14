package com.blog.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.blog.domin.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public String getToken(User user){
        Date start =new Date();
        long currentTime=System.currentTimeMillis();
        Date end=new Date(currentTime);
        String token="";
        token = JWT.create().withAudience(user.getId()+"").withIssuedAt(start).withExpiresAt(end).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
