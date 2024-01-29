package com.example.java4.controllers;
import com.example.java4.dto.hoaDon.StoreRequest;
import com.example.java4.entities.HoaDon;
import com.example.java4.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("hoa_don")
public class HoaDonController {
    ArrayList<com.example.java4.dto.hoaDon.StoreRequest> ds;
    HoaDon hdRem = new HoaDon();
    StoreRequest rem = new StoreRequest();
    @Autowired
    private HoaDonRepository hdRepo;
    @Autowired
    private NhanVienRepository nvRepo;
    @Autowired
    private KhachHangRepository khRepo;
    @Autowired
    private HoaDonFullRepository hdFullRepo;
    public HoaDonController() {
        System.out.println("start new cycle");
        this.ds = new ArrayList<>();
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("data",hdFullRepo.findAll());
        return "admin/ql_hoa_don/Index";
    }

    @GetMapping("update/{id}")
    public String update(Model model, @PathVariable(value = "id") HoaDon hoaDon) {
        StoreRequest hstrReq = new StoreRequest();
        model.addAttribute("dsKhachHang",khRepo.findAll());
        model.addAttribute("dsNhanVien", nvRepo.findAll());
        model.addAttribute("data", hoaDon);
        return "admin/ql_hoa_don/Edit";
    }

    @PostMapping("update/{id}")
    public String doUpdate(
            Model model,
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result, @PathVariable(value = "id") HoaDon hd
    ) {
        if (result.hasErrors()){
            model.addAttribute("dsKhachHang",khRepo.findAll());
            model.addAttribute("dsNhanVien", nvRepo.findAll());
            return "admin/ql_hoa_don/Edit";
        }
        else{
            hd.setIdKH(req.getIdKH());
            hd.setIdNV(req.getIdNV());
            hd.setNgayMuaHang(req.getNgayMuaHang());
            hd.setTrangThai(req.getTrangThai());
            hdRepo.save(hd);
            return "redirect:/hoa_don/index";
        }
    }

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") HoaDon hd) {
        hdRepo.delete(hd);
        return "redirect:/hoa_don/index";
    }

    @GetMapping("/create")
    public String getSanPhamForm(Model model) {
        model.addAttribute("dsKhachHang",khRepo.findAll());
        model.addAttribute("dsNhanVien", nvRepo.findAll());
        model.addAttribute("data", rem);
        return "admin/ql_hoa_don/Create";
    }

    @PostMapping("store")
    public String create(
            Model model,
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        HoaDon hd = new HoaDon();
        if (result.hasErrors()){
            model.addAttribute("dsKhachHang",khRepo.findAll());
            model.addAttribute("dsNhanVien", nvRepo.findAll());
            return "admin/ql_hoa_don/Create";
        }
        else{
            hd.setId(null);
            hd.setIdKH(req.getIdKH());
            hd.setIdNV(req.getIdNV());
            hd.setNgayMuaHang(req.getNgayMuaHang());
            hd.setTrangThai(req.getTrangThai());
            hdRepo.save(hd);
            return "redirect:/hoa_don/index";
        }
    }
}