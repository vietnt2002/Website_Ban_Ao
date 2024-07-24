package com.example.java4.controller.QLKM;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("qlkm")
public class QLKMController {
    @GetMapping("")
    public String hienThi(Model model, @RequestParam(value = "page", defaultValue = "0") String pageParam) {
        return "/view/QLKM/khuyenMai.jsp";
    }
    @GetMapping("{idKM}")
    public String detailSP(Model model, @PathVariable String idSP) {
        return "/view/QLKM/detailKM.jsp";
    }
}