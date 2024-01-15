package com.example.java4.controllers;

import com.example.java4.dto.nhan_vien.StoreRequest;
import com.example.java4.model.NhanVien;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("nhan_vien")
public class NhanVienController {
    ArrayList<StoreRequest> ds;
    StoreRequest rem;
    public NhanVienController() {
        this.ds = new ArrayList<>();
        this.rem = new StoreRequest();
        ds.add(new StoreRequest("1","Loc","nv1","phuclocub","loc123","working"));
        ds.add(new StoreRequest("2","Linh","nv2","linhnt01","linh123","working"));
        ds.add(new StoreRequest("3","Lan","nv3","lanvt02","lan123","working"));
        ds.add(new StoreRequest("4","Hung","nv4","hungtt05","hung123","working"));
        ds.add(new StoreRequest("5","Tai","nv5","tailv06","tai123","working"));
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("data", rem);
        return "admin/ql_nhan_vien/Create";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("data", ds);
        return "admin/ql_nhan_vien/Index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value="id")String id) {
        for (int i = 0; i < ds.size(); i++) {
             if(ds.get(i).getId().equals(id)){
                 ds.remove(i);
             }
        }
        return "redirect:/nhan_vien/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model,@PathVariable(value ="id") String id) {
        StoreRequest newReq = new StoreRequest();
        for (int i = 0; i < ds.size(); i++) {
            if(ds.get(i).getId().equals(id)){
                newReq = ds.get(i);
            }
        }
        model.addAttribute("data",newReq);
        return "admin/ql_nhan_vien/Edit";
    }

    @PostMapping("/update/{id}")
    public String doUpdate(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result,@PathVariable(value="id") String id
    ) {
        if (result.hasErrors()) {
            return "admin/ql_nhan_vien/Edit";
        }
        else{
            for (int i = 0; i < ds.size() ; i++) {
                if(ds.get(i).getId().equals(id)){
                    ds.get(i).setTen(req.getTen());
                    ds.get(i).setMaNV(req.getMaNV());
                    ds.get(i).setTenDangNhap(req.getTenDangNhap());
                    ds.get(i).setMatKhau(req.getMatKhau());
                    ds.get(i).setTrangThai(req.getTrangThai());
                }
            }
            return "redirect:/nhan_vien/index";
        }
    }
    @PostMapping("store")
    public String create(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/ql_nhan_vien/Create";
        }
        else{
            rem = req;
            ds.add(req);
            return "redirect:/nhan_vien/index";
        }
    }
}