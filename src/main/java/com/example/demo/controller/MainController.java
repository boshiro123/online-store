package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String logIn () {
        return "logIn.html";
    }
    @GetMapping("/api/vi")
    public String storeMenu(){
        return "store.html";
    }

}
