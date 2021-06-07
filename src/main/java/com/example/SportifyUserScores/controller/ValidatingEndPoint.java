package com.example.SportifyUserScores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.SportifyUserScores.util.JwtUtil;


@RestController

public class ValidatingEndPoint {

    @Autowired
    final JwtUtil jwtUtil;
    String email;

    public ValidatingEndPoint(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestHeader(name = "Authorization") String jwt) {
        email = jwtUtil.extractEmail(jwt);

        return email;

    }
}



