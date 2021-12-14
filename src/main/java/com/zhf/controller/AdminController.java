package com.zhf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 曾鸿发
 * @create: 2021-11-18 21:11
 * @description：Admin 接口层
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/demo")
    public String demo(){
        return "spring security demo";
    }
}
