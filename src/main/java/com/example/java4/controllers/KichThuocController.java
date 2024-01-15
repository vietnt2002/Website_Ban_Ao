package com.example.java4.controllers;
import com.example.java4.dto.kich_thuoc.StoreRequest;
import com.example.java4.model.KichThuoc;
import com.example.java4.model.MauSac;
import jakarta.validation.Valid;
import org.apache.catalina.Store;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@Controller
@RequestMapping("kich_thuoc")
public class KichThuocController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    ArrayList<StoreRequest> ds = new ArrayList<>();
    StoreRequest rem;
    StoreRequest remUpdate;
    public KichThuocController() {
        this.rem = new StoreRequest();
        this.remUpdate = new StoreRequest();
        this.ds = new ArrayList<>();
        ds.add(new StoreRequest("1","kt1","large","available"));
        ds.add(new StoreRequest("2","kt2","min","available"));
        ds.add(new StoreRequest("3","kt3","medium","available"));
        ds.add(new StoreRequest("4","kt4","average","available"));
        ds.add(new StoreRequest("5","kt5","oversize","not available"));
    }
    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("data",rem);
        return "admin/ql_kich_thuoc/Create";
    }

    @GetMapping("/index")
        public String getIndexPage(Model model){
        model.addAttribute("data",ds);
        return "admin/ql_kich_thuoc/Index";
        }
    @GetMapping("/delete/{id}")
        public String delete(Model model, @PathVariable(value="id")String id){
        for (int i = 0; i <ds.size() ; i++) {
            if(ds.get(i).getId().equals(id)){
                 ds.remove(i);
            }
        }
        return "redirect:/kich_thuoc/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model,@PathVariable(value = "id") String id){
        StoreRequest newReq = new StoreRequest();
        model.addAttribute("data",remUpdate);
        for (int i = 0; i < ds.size(); i++) {
            if(ds.get(i).getId().equals(id)){
                 newReq = ds.get(i);
            }
        }
        model.addAttribute("data",newReq);
        return "admin/ql_kich_thuoc/Edit";
    }

    @PostMapping("/update/{id}")
    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req, BindingResult rs, @PathVariable(value="id")String id){
        if(rs.hasErrors()){
             return "admin/ql_kich_thuoc/Edit";
        }
        else {
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getId().equals(id)) {
                    ds.get(i).setMa(req.getMa());
                    ds.get(i).setTen(req.getTen());
                    ds.get(i).setTrangThai(req.getTrangThai());
                }
                remUpdate = req;
            }
            return "redirect:/kich_thuoc/index";
        }
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        StoreRequest kt = new StoreRequest(req.getId(),req.getTen(),req.getMa(),req.getTrangThai());
        rem = kt;
        if(result.hasErrors()){
            return "admin/ql_kich_thuoc/Create";
        }
        else{
            ds.add(rem);
            return "redirect:/kich_thuoc/index";
        }
    }
}