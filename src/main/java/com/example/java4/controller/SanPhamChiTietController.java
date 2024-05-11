package com.example.java4.controller;

import com.example.java4.repositories.SPCTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("spct")
public class
SanPhamChiTietController {

    @Autowired
    SPCTRepository spctRepo;

    @GetMapping("/index")
    public String index() {
        return "/bootstrap-shop-template/detailSanPhamChiTiet.jsp";
    }

}