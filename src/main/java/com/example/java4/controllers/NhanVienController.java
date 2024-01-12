package com.example.java4.controllers;
import com.example.java4.dto.nhan_vien.StoreRequest;
import com.example.java4.model.NhanVien;
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
@RequestMapping("nhan_vien")
public class NhanVienController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    ArrayList<NhanVien> lstNhanViens = new ArrayList<>();
    NhanVien nvRem = new NhanVien();
    public NhanVienController() {

    }
    @GetMapping("/create")
    public String create(Model model)
    {
        String name = "SD18203";
        model.addAttribute("lstNhanVien",lstNhanViens);
        model.addAttribute("data",nvRem);
        return "admin/ql_nhan_vien/Create";
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
        NhanVien nv = new NhanVien(req.getId(), req.getTen(),req.getMaNV(),req.getTenDangNhap(),req.getMatKhau(),req.getTrangThai());
        System.out.println(nv);
        nvRem = nv;
        lstNhanViens.add(nv);
        System.out.println(result.getAllErrors());
        return "admin/ql_nhan_vien/Create";
    }
}