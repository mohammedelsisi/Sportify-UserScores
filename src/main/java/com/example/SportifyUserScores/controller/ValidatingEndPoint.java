package com.example.SportifyUserScores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.SportifyUserScores.util.JwtUtil;

@RestController
public class ValidatingEndPoint {

    @Autowired
    final JwtUtil jwtUtil;
    String jwt = null;
    String email= null;

    @RequestMapping("/hello")
    public String hello(){
        email = jwtUtil.extractEmail(jwt);
        return email; }
}
