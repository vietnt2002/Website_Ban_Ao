package com.example.java4.controllers;

import com.example.java4.dto.khach_hang.StoreRequest;
import com.example.java4.model.KhachHang;
import com.example.java4.model.KichThuoc;
import jakarta.validation.Valid;
import org.apache.catalina.Store;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("khach_hang")
public class KhachHangController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    ArrayList<StoreRequest> ds;
    StoreRequest rem;

    public KhachHangController() {
        this.ds = new ArrayList<>();
        this.rem = new StoreRequest();
        ds.add(new StoreRequest("1", "Linh", "0333336666", "kh1", "active"));
        ds.add(new StoreRequest("2", "Loc", "0333322222", "kh2", "active"));
        ds.add(new StoreRequest("3", "Quang", "0333334535", "kh3", "active"));
        ds.add(new StoreRequest("4", "Lan", "0333335353", "kh4", "active"));
        ds.add(new StoreRequest("5", "Hung", "0333335353", "kh5", "active"));
        ds.add(new StoreRequest("6", "Tai", "0333338878", "kh6", "active"));
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("data", rem);
        return "admin/ql_khach_hang/Create";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("data", ds);
        return "admin/ql_khach_hang/Index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable(value = "id") String id) {
        StoreRequest newReq = new StoreRequest();
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getId().equals(id)) {
                newReq = ds.get(i);
            }
        }
        model.addAttribute("data", newReq);
        return "admin/ql_khach_hang/Edit";
    }

    @PostMapping("/update/{id}")
    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req,
                           BindingResult result, @PathVariable(value = "id") String id) {
        if (result.hasErrors()) {
            return "admin/ql_khach_hang/Edit";
        } else {
            for (int i = 0; i < ds.size() ; i++) {
                if(ds.get(i).getId().equals(id)){
                    ds.get(i).setTen(req.getTen());
                    ds.get(i).setSdt(req.getSdt());
                    ds.get(i).setMaKH(req.getMaKH());
                    ds.get(i).setTrangThai(req.getTrangThai());
                }
            }
            return "redirect:/khach_hang/index";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") String id) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getId().equals(id)) {
                 ds.remove(i);
            }
        }
        return "redirect:/khach_hang/index";
    }


    @PostMapping("store")
    public String create(
//        @RequestParam("email") Optional<String> username,
//        @RequestParam("password") String pwd,
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/ql_khach_hang/Create";
        } else {
            rem = req;
            ds.add(req);
            return "redirect:/khach_hang/index";
        }
    }
}