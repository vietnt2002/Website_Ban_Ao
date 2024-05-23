package com.example.java4.controller.controller_khang;

import com.example.java4.entities.NhanVien;
import com.example.java4.repositories.NhanVienRepository;
import com.example.java4.request.req_khang.NhanVienRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @GetMapping("")
    public String hienThiNhanVien(Model model){

        List<NhanVien> list = nhanVienRepository.findAll();
        model.addAttribute("listNV",list);
        return "/view_khang/ql_nhan_vien/index";
    }

    @GetMapping("create")
    public String create(Model model){
        NhanVienRequest request = new NhanVienRequest();
        model.addAttribute("data",request);
        return "/view_khang/ql_nhan_vien/create";
    }

    @PostMapping("store")
    public String storeNhanVien(@Valid @ModelAttribute("data")NhanVienRequest request,
                                BindingResult result){

        if (result.hasErrors()){
            return "/view_khang/ql_nhan_vien/create";
        }else {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMa(request.getMa());
            nhanVien.setTen(request.getTen());
            nhanVien.setTenDangNhap(request.getTenDangNhap());
            nhanVien.setMatKhau(request.getMatKhau());
            nhanVien.setTrangThai(request.getTrangThai());
            nhanVienRepository.save(nhanVien);
            return "redirect:/nhan-vien";
        }

    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") NhanVien nv, Model model)
    {
        model.addAttribute("data", nv);
        return "/view_khang/ql_nhan_vien/edit";
    }


    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") NhanVien nv,
            @Valid @ModelAttribute("data") NhanVienRequest req,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "/view_khang/ql_nhan_vien/create";
        }

        nv.setMa(req.getMa());
        nv.setTen(req.getTen());
        nv.setTenDangNhap(req.getTenDangNhap());
        nv.setMatKhau(req.getMatKhau());
        nv.setTrangThai(req.getTrangThai());
        this.nhanVienRepository.save(nv);

        return "redirect:/nhan-vien";
    }


    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int idCanXoa)
    {
        this.nhanVienRepository.deleteById(idCanXoa);
        return "redirect:/nhan-vien";
    }

}
