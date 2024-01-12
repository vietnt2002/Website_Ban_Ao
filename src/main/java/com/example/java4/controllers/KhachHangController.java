package com.example.java4.controllers;

import com.example.java4.dto.khach_hang.StoreRequest;
import com.example.java4.model.KhachHang;
import com.example.java4.model.KichThuoc;
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
@RequestMapping("khach_hang")
public class KhachHangController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    ArrayList<KhachHang> lstKhachHangs = new ArrayList<>();
    KhachHang khRem = new KhachHang();
    public KhachHangController() {

    }
    @GetMapping("/create")
    public String create(Model model)
    {
        String name = "SD18203";
        model.addAttribute("lstKhachHang",lstKhachHangs);
        model.addAttribute("data",khRem);
        return "admin/ql_khach_hang/Create";
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
        KhachHang kh = new KhachHang(req.getId(),req.getTen(),req.getSdt(),req.getMaKH(),req.getTrangThai());
        System.out.println(kh);
        khRem = kh;
        lstKhachHangs.add(kh);
        System.out.println(result.getAllErrors());
        return "admin/ql_khach_hang/Create";
    }
}