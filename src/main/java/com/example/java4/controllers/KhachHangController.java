package com.example.java4.controllers;

import com.example.java4.dto.khach_hang.StoreRequest;
import com.example.java4.entities.KhachHang;
import com.example.java4.repositories.KhachHangRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("khach_hang")
public class KhachHangController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    List<KhachHang> ds;
    StoreRequest rem;
    @Autowired
    KhachHangRepository khRepo;
    public KhachHangController() {
        this.rem = new StoreRequest();
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("data", rem);
        return "admin/ql_khach_hang/Create";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("data", khRepo.findAll());
        return "admin/ql_khach_hang/Index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable(value = "id") KhachHang kh) {
        model.addAttribute("data", kh);
        return "admin/ql_khach_hang/Edit";
    }

    @PostMapping("/update/{id}")
    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req,
                           BindingResult result, @PathVariable(value = "id") KhachHang kh) {
        if (result.hasErrors()) {
            return "admin/ql_khach_hang/Edit";
        } else {
            kh.setMa(req.getMa());
            kh.setSdt(req.getSdt());
            kh.setTen(req.getTen());
            kh.setTrangThai(req.getTrangThai());
            khRepo.save(kh);
            return "redirect:/khach_hang/index";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") KhachHang kh) {
        khRepo.delete(kh);
        return "redirect:/khach_hang/index";
    }

    @PostMapping("store")
    public String create(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        KhachHang newKh = new KhachHang();
        if (result.hasErrors()) {
            return "admin/ql_khach_hang/Create";
        } else {
            newKh.setId(null);
            newKh.setMa(req.getMa());
            newKh.setTen(req.getTen());
            newKh.setSdt(req.getSdt());
            newKh.setTrangThai(req.getTrangThai());
            rem = req;
            khRepo.save(newKh);
            return "redirect:/khach_hang/index";
        }
    }
}