package com.example.java4.controllers;

import com.example.java4.dto.kich_thuoc.StoreRequest;
import com.example.java4.model.KichThuoc;
import com.example.java4.model.MauSac;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("kich_thuoc")
public class KichThuocController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    ArrayList<KichThuoc> lstKichThuoc = new ArrayList<>();
    KichThuoc ktRem = new KichThuoc();
    public KichThuocController() {

    }
    @GetMapping("/create")
    public String getSanPhamForm(Model model)
    {
        String name = "SD18203";
        model.addAttribute("lstKichThuoc",lstKichThuoc);
        model.addAttribute("data",ktRem);
        return "admin/ql_kich_thuoc/Create";
    }
    @PostMapping("store")
    public String create(
//        @RequestParam("email") Optional<String> username,
//        @RequestParam("password") String pwd,
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            System.out.println("Có lỗi");
        }
        System.out.println(req.getId());
        System.out.println(req.getTen());
        System.out.println(req.getMa());
        System.out.println(req.getTrangThai());
        KichThuoc kt = new KichThuoc(req.getId(),req.getTen(),req.getMa(),req.getTrangThai());
        ktRem = kt;
        lstKichThuoc.add(kt);
        System.out.println(result.getAllErrors());
        return "admin/ql_kich_thuoc/Create";
    }
}