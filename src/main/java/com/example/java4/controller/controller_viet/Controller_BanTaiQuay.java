package com.example.java4.controller.controller_viet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("store")
public class Controller_BanTaiQuay {

    @GetMapping("admin/ban-tai-quay")
    public String getBanTaiQuay(){
        return "/view/view_viet/banHangTaiQuay.jsp";
    }
}
