package com.example.java4.controllers;

import com.example.java4.dto.spct.StoreRequest;
import com.example.java4.entities.*;
import com.example.java4.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("spct")
public class SPCTController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    StoreRequest rem;
    @Autowired
    SanPhamRepository spRepo;
    @Autowired
    MauSacRepository msRepo;
    @Autowired
    KichThuocRepository ktRepo;
    @Autowired
    MauSacRepository mauSacRepo;
    @Autowired
    SPCTRepository spctRepo;
    @Autowired
    SPCTfullRepository spcTfullRepository;
//    public ArrayList<KichThuoc> loadToKichThuocCbo() {
//        ArrayList<KichThuoc> lstKichThuoc = new ArrayList<>();
//        lstKichThuoc.add(new KichThuoc(1, "kt1", "large", 1));
//        lstKichThuoc.add(new KichThuoc(2, "kt2", "min", 1));
//        lstKichThuoc.add(new KichThuoc(3, "kt3", "medium", 1));
//        lstKichThuoc.add(new KichThuoc(4, "kt4", "average", 0));
//        lstKichThuoc.add(new KichThuoc(5, "kt5", "oversize", 1));
//        return lstKichThuoc;
//    }
//
//    public ArrayList<MauSac> loadMauSacCbo() {
//        ArrayList<MauSac> lstMauSac = new ArrayList<>();
//        lstMauSac.add(new MauSac(1, "bl1", "Black",1));
//        lstMauSac.add(new MauSac(2, "Vi1", "Violet",0));
//        lstMauSac.add(new MauSac(3, "Ye1", "Yellow",1));
//        lstMauSac.add(new MauSac(4, "Pi1", "Pink",0));
//        lstMauSac.add(new MauSac(5, "Gry1", "Gray",0));
//        return lstMauSac;
//    }
//
//    public ArrayList<SanPham> loadSanPhamCbo() {
//        ArrayList<SanPham> lstSanPham = new ArrayList<>();
//        lstSanPham.add(new SanPham("001", "SP1", "Wallet", "Stocking"));
//        lstSanPham.add(new SanPham("002", "SP2", "Learther strap apple watch", "Stocking"));
//        lstSanPham.add(new SanPham("003", "SP3", "Clutch", "Stocking"));
//        lstSanPham.add(new SanPham("004", "SP4", "Belt", "Stocking"));
//        lstSanPham.add(new SanPham("005", "SP5", "Leather case", "Stocking"));
//        lstSanPham.add(new SanPham("006", "SP6", "Sticky leather(optional size)", "Stocking"));
//        return lstSanPham;
//    }

//    public SPCT reqToModel(StoreRequest streq) {
//        Optional<KichThuoc> kichThuoc = ktRepo.findById(streq.getIdKichThuoc());;
//        Optional<MauSac> mauSac = mauSacRepo.findById(streq.getIdMauSac());
//        Optional<SanPham> sanPham = spRepo.findById(streq.getIdSanPham());
//        SPCT spct = new SPCT(streq.getId(), mauSac.get().getId(), kichThuoc.get().getId(),sanPham.get().getId(), streq.getMaSPCT(), streq.getSoLuong(), streq.getDonGia(), streq.getTrangThai());
//        return spct;
//    }

    public SPCTController() {
        rem = new StoreRequest();
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("data", rem);
        model.addAttribute("dsKichThuoc",ktRepo.findAll());
        model.addAttribute("dsMauSac", msRepo.findAll());
        model.addAttribute("dsSanPham",spRepo.findAll());
        return "admin/ql_spct/Create";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("data",spcTfullRepository.findAll());
        return "admin/ql_spct/Index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") SPCT spct) {
        spctRepo.delete(spct);
        return "redirect:/spct/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable(value = "id") SPCT spct) {
        model.addAttribute("dsKichThuoc",ktRepo.findAll());
        model.addAttribute("dsMauSac", msRepo.findAll());
        model.addAttribute("dsSanPham",spRepo.findAll());
        model.addAttribute("data",spct);
        return "admin/ql_spct/Edit";
    }

    @PostMapping("/update/{id}")
    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req, BindingResult result, @PathVariable(value = "id") SPCT spct, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("dsKichThuoc",ktRepo.findAll());
            model.addAttribute("dsMauSac", msRepo.findAll());
            model.addAttribute("dsSanPham",spRepo.findAll());
            return "admin/ql_spct/Edit";
        } else {
//            SPCT spctClone = reqToModel(req);
            spct.setMaSPCT(req.getMaSPCT());
            spct.setSoLuong(req.getSoLuong());
            spct.setTrangThai(req.getTrangThai());
            spct.setDonGia(req.getDonGia());
            spct.setIdMauSac(req.getIdMauSac());
            spct.setIdSanPham(req.getIdSanPham());
            spct.setIdKichThuoc(req.getIdKichThuoc());
            spctRepo.save(spct);
            return "redirect:/spct/index";
        }
    }

    @PostMapping("store")
    public String Store(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result, Model model
    ) {
        System.out.println(req.toString());
        SPCT spct = new SPCT();
        if (result.hasErrors()) {
            model.addAttribute("dsKichThuoc",ktRepo.findAll());
            model.addAttribute("dsMauSac", msRepo.findAll());
            model.addAttribute("dsSanPham",spRepo.findAll());
            return "admin/ql_spct/Create";
        } else {
            spct.setMaSPCT(req.getMaSPCT());
            spct.setSoLuong(req.getSoLuong());
            spct.setTrangThai(req.getTrangThai());
            spct.setDonGia(req.getDonGia());
            spct.setIdMauSac(req.getIdMauSac());
            spct.setIdSanPham(req.getIdSanPham());
            spct.setIdKichThuoc(req.getIdKichThuoc());
            spctRepo.save(spct);
            return "redirect:/spct/index";
        }
    }
}