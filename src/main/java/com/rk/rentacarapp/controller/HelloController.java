package com.rk.rentacarapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller used for testing

@RestController
@RequestMapping(value = "test")
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world";
    }
}
