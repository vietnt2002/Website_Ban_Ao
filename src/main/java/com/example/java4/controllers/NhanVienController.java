package com.example.java4.controllers;

import com.example.java4.dto.nhan_vien.StoreRequest;
import com.example.java4.entities.NhanVien;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.NhanVienRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
@RequestMapping("nhan_vien")
public class NhanVienController {
    StoreRequest rem;
    @Autowired
    NhanVienRepository nvRepo;
    public NhanVienController() {
        this.rem = new StoreRequest();
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("data", rem);
        return "admin/ql_nhan_vien/Create";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("data",nvRepo.findAll());
        return "admin/ql_nhan_vien/Index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value="id")NhanVien nv) {
        nvRepo.delete(nv);
        return "redirect:/nhan_vien/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model,@PathVariable(value ="id") NhanVien nv) {
        model.addAttribute("data",nv);
        return "admin/ql_nhan_vien/Edit";
    }

    @PostMapping("/update/{id}")
    public String doUpdate(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result,@PathVariable(value="id") NhanVien nv
    ) {
        if (result.hasErrors()) {
            return "admin/ql_nhan_vien/Edit";
        }
        else{
            nv.setMa(req.getMa());
            nv.setTen(req.getTen());
            nv.setTenDangNhap(req.getTenDangNhap());
            nv.setMatKhau(req.getMatKhau());
            nv.setTrangThai(req.getTrangThai());
            nvRepo.save(nv);
            return "redirect:/nhan_vien/index";
        }
    }
    @PostMapping("store")
    public String create(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        NhanVien newNV = new NhanVien();
        if (result.hasErrors()) {
            return "admin/ql_nhan_vien/Create";
        }
        else{
            rem = req;
            newNV.setId(null);
            newNV.setMa(req.getMa());
            newNV.setTen(req.getTen());
            newNV.setTenDangNhap(req.getTenDangNhap());
            newNV.setMatKhau(req.getMatKhau());
            newNV.setTrangThai(req.getTrangThai());
            nvRepo.save(newNV);
            return "redirect:/nhan_vien/index";
        }
    }
}