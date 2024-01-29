package com.example.java4.controllers;

import com.example.java4.dto.mau_sac.StoreRequest;
import com.example.java4.entities.MauSac;
import com.example.java4.repositories.MauSacRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
@RequestMapping("mau_sac")
public class MauSacController {
    ArrayList<StoreRequest> ds;
    MauSac msRem = new MauSac();
    StoreRequest msRemUpdate = new StoreRequest();
    @Autowired
    private MauSacRepository msRepo;
    public MauSacController() {
        System.out.println("start new cycle");
        this.ds = new ArrayList<>();
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("data",msRepo.findAll());
        return "admin/ql_mau_sac/Index";
    }

    @GetMapping("index/update-mauSac/{id}")
    public String update(Model model, @PathVariable(value = "id") MauSac mauSac) {
        StoreRequest hstrReq = new StoreRequest();
        model.addAttribute("data", mauSac);
        return "admin/ql_mau_sac/Edit";
    }

    @PostMapping("index/update-mauSac/{id}")
    public String doUpdate(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result, @PathVariable(value = "id") MauSac ms
    ) {
        if (result.hasErrors()) return "admin/ql_mau_sac/Edit";
        else{
            msRemUpdate = req;
            System.out.println("update post check id:"+ms.getId());
            ms.setTen(req.getTen());
            ms.setMa(req.getMa());
            ms.setTrangThai(req.getTrangThai());
            msRepo.save(ms);
            return "redirect:/mau_sac/index";
        }
    }

    @GetMapping("index/delete-mauSac/{id}")
    public String delete(Model model, @PathVariable(value = "id") MauSac ms) {
        msRepo.delete(ms);
        return "redirect:/mau_sac/index";
    }

    @GetMapping("/create")
    public String getSanPhamForm(Model model) {
        model.addAttribute("data", msRem);
        return "admin/ql_mau_sac/Create";
    }

    @PostMapping("store")
    public String create(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        MauSac msCpy = new MauSac(req.getId(), req.getTen(), req.getMa(), req.getTrangThai());
        msRem = msCpy;
        if (result.hasErrors()) return "admin/ql_mau_sac/Create";
        else{
            MauSac ms = new MauSac(null, req.getTen(), req.getMa(),1);
            msRepo.save(ms);
            return "redirect:/mau_sac/index";
        }
    }
}