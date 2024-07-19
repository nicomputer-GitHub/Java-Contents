package com.example.linebot.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Push {
    @GetMapping("test")
    public String hello(HttpServletRequest request) {
        return "Get from " + request.getRequestURL();
    }
}
