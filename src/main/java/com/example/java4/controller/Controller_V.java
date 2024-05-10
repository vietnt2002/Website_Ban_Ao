package com.example.java4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("store")
public class Controller_V {


    @GetMapping("trang-chu")
    public String getTrangChu(){
        return "trangChu";
    }

}
