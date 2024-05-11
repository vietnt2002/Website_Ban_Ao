package com.example.java4.controller.controller_tai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckOutController {
    @GetMapping("/index")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }




}