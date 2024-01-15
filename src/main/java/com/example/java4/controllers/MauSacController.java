package com.example.java4.controllers;

import com.example.java4.dto.mau_sac.StoreRequest;
import com.example.java4.model.MauSac;
import jakarta.validation.Valid;
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

    public MauSacController() {
        System.out.println("start new cycle");
        this.ds = new ArrayList<>();
        ds.add(new StoreRequest("1", "bl1", "Black"));
        ds.add(new StoreRequest("2", "Vi1", "Violet"));
        ds.add(new StoreRequest("3", "Ye1", "Yellow"));
        ds.add(new StoreRequest("4", "Pi1", "Pink"));
    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("ds", ds);
        return "admin/ql_mau_sac/Index";
    }

    @GetMapping("index/update-mauSac/{id}")
    public String update(Model model, @PathVariable(value = "id") String id) {
        StoreRequest hstrReq = new StoreRequest();
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getId().equals(id)) {
                hstrReq = ds.get(i);
            }
        }
        msRemUpdate = hstrReq;
        System.out.println(id);
        model.addAttribute("data", msRemUpdate);
        return "admin/ql_mau_sac/Edit";
    }

    @PostMapping("index/update-mauSac/{id}")
    public String doUpdate(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result, @PathVariable(value = "id") String id
    ) {
        if (result.hasErrors()) return "admin/ql_mau_sac/Edit";
        else{
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(id)) {
                    System.out.println("do update");
                    ds.get(i).setId(req.getId());
                    ds.get(i).setTen(req.getTen());
                    ds.get(i).setMa(req.getMa());
                }
            }
            msRemUpdate = req;
            return "redirect:/mau_sac/index";
        }
    }

    @GetMapping("index/delete-mauSac/{id}")
    public String delete(Model model, @PathVariable(value = "id") String id) {
        System.out.println(id);
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getId().equals(id)) {
                ds.remove(i);
                System.out.println("do delete");
            }
        }
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
        MauSac ms = new MauSac(req.getId(), req.getTen(), req.getMa());
        msRem = ms;
        if (result.hasErrors()) return "admin/ql_mau_sac/Create";
        else{
            ds.add(req);
            return "redirect:/mau_sac/index";
        }
    }
}