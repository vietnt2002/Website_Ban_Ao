package com.example.java4.controllers;

import com.example.java4.dto.spct.StoreRequest;
import com.example.java4.model.KichThuoc;
import com.example.java4.model.MauSac;
import com.example.java4.model.SanPham;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("spct")
public class SPCTController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    ArrayList<StoreRequest> ds;
    StoreRequest rem;
    public ArrayList<KichThuoc> loadToKichThuocCbo() {
        ArrayList<KichThuoc> lstKichThuoc = new ArrayList<>();
        lstKichThuoc.add(new KichThuoc("1", "kt1", "large", "available"));
        lstKichThuoc.add(new KichThuoc("2", "kt2", "min", "available"));
        lstKichThuoc.add(new KichThuoc("3", "kt3", "medium", "available"));
        lstKichThuoc.add(new KichThuoc("4", "kt4", "average", "available"));
        lstKichThuoc.add(new KichThuoc("5", "kt5", "oversize", "not available"));
        return lstKichThuoc;
    }

    public ArrayList<MauSac> loadMauSacCbo(){
        ArrayList<MauSac> lstMauSac = new ArrayList<>();
        lstMauSac.add(new MauSac("1", "bl1", "Black"));
        lstMauSac.add(new MauSac("2", "Vi1", "Violet"));
        lstMauSac.add(new MauSac("3", "Ye1", "Yellow"));
        lstMauSac.add(new MauSac("4", "Pi1", "Pink"));
        lstMauSac.add(new MauSac("5", "Gry1", "Gray"));
        return lstMauSac;
    }

    public ArrayList<SanPham> loadSanPhamCbo(){
        ArrayList<SanPham> lstSanPham  = new ArrayList<>();
        lstSanPham.add(new SanPham("001","SP1","Wallet","Stocking"));
        lstSanPham.add(new SanPham("002","SP2","Learther strap apple watch","Stocking"));
        lstSanPham.add(new SanPham("003","SP3","Clutch","Stocking"));
        lstSanPham.add(new SanPham("004","SP4","Belt","Stocking"));
        lstSanPham.add(new SanPham("005","SP5","Leather case","Stocking"));
        lstSanPham.add(new SanPham("006","SP6","Sticky leather(optional size)","Stocking"));
        return lstSanPham;
    }

    public SPCTController() {
        this.ds = new ArrayList<>();
        rem = new StoreRequest();
        ArrayList<KichThuoc> dsKichThuoc = loadToKichThuocCbo();
        ArrayList<MauSac> dsMauSac = loadMauSacCbo();
        ArrayList<SanPham> dsSanPham = loadSanPhamCbo();
        ds.add(new StoreRequest("1","SPCT1",dsKichThuoc.get(1),dsMauSac.get(1),dsSanPham.get(1),1,150.5,"stocking"));
        ds.add(new StoreRequest("2","SPCT2",dsKichThuoc.get(2),dsMauSac.get(2),dsSanPham.get(2),3,250.5,"stocking"));
        ds.add(new StoreRequest("3","SPCT3",dsKichThuoc.get(3),dsMauSac.get(3),dsSanPham.get(3),5,50.5,"stocking"));
        ds.add(new StoreRequest("4","SPCT4",dsKichThuoc.get(4),dsMauSac.get(4),dsSanPham.get(4),6,100.5,"stocking"));
        ds.add(new StoreRequest("5","SPCT5",dsKichThuoc.get(2),dsMauSac.get(1),dsSanPham.get(3),7,120.5,"stocking"));
        ds.add(new StoreRequest("6","SPCT6",dsKichThuoc.get(4),dsMauSac.get(3),dsSanPham.get(2),3,110.5,"stocking"));
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("data",rem);
        return "admin/ql_san_pham/Create";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("data", ds);
        return "admin/ql_san_pham/Index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") String id) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getId().equals(id)) {
                ds.remove(i);
            }
        }
        return "redirect:/san_pham/index";
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
        return "admin/ql_san_pham/Edit";
    }
//
//    @PostMapping ("/update/{id}")
//    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req,BindingResult result,@PathVariable(value ="id") String id) {
////        if (result.hasErrors()) {
////            return "admin/ql_san_pham/Edit";
////        }
////        else{
////            for (int i = 0; i < ds.size(); i++) {
////                if(ds.get(i).getId().equals(id)){
////                    ds.get(i).setMa(req.getMa());
////                    ds.get(i).setTen(req.getTen());
////                    ds.get(i).setTrangThai(req.getTrangThai());
////                }
////            }
////            rem = req;
////            return  "redirect:/san_pham/index";
////        }
//    }

    @PostMapping("store")
    public String Store(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            System.out.println("Có lỗi");
            return "admin/ql_san_pham/Create";
        } else {
            rem = req;
            ds.add(req);
            return "redirect:/san_pham/index";
        }
    }
}