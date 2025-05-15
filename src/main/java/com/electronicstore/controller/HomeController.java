package com.electronicstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeController {

    public String testing(){
        System.out.println("hello");
        System.out.println("helllllllo");
        return "welcome to store";
    }
}
