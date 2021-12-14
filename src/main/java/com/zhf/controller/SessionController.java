package com.zhf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 曾鸿发
 * @create: 2021-11-22 09:38
 * @description：会话管理
 **/
@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String sessionInvalid(){
        return "session失效";
    }
}
