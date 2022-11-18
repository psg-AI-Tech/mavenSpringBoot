package com.example.mavenSpringBoot.controller;

import java.util.HashMap;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
// @EnableAutoConfiguration 
// @ComponentScan
// @SpringBootApplication
public class TestController {
   
    @RequestMapping("/html1")
    @ResponseBody
    public String helloWorldHome(){
        // return "index";
        return "hello world, vscode";
    }
    @RequestMapping("/")
    // @ResponseBody
    public String helloHtml(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        // return "x";
        return "test/index_copy";
    }
}