package com.example.java4.controller.QLSP;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("qlsp")
public class QLSPController {
    @GetMapping("")
    public String hienThi(Model model, @RequestParam(value = "page", defaultValue = "0") String pageParam) {
        return "/view/QLSP/sanPham.jsp";
    }
}
