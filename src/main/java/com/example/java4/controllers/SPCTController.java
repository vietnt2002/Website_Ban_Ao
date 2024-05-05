package com.example.java4.controllers;

import com.example.java4.dto.spct.StoreRequest;
import com.example.java4.entities.*;
import com.example.java4.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    //API
    @GetMapping("hien-thi")
    public ResponseEntity<?> hienThi(){
        return ResponseEntity.ok(spctRepo.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody @Valid StoreRequest req,
                                    BindingResult result){
        SPCT spct = new SPCT();
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.ok(list);
        }else {
            spct.setMaSPCT(req.getMaSPCT());
            spct.setSoLuong(req.getSoLuong());
            spct.setTrangThai(req.getTrangThai());
            spct.setDonGia(req.getDonGia());
            spct.setIdMauSac(req.getIdMauSac());
            spct.setIdSanPham(req.getIdSanPham());
            spct.setIdKichThuoc(req.getIdKichThuoc());
            spctRepo.save(spct);
            return ResponseEntity.ok("Thêm thành công");
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody @Valid StoreRequest req,
                                    BindingResult result){
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.ok(list);
        }else {
            Optional<SPCT> optional = spctRepo.findById(id);
            SPCT spct = optional.get();
            spct.setMaSPCT(req.getMaSPCT());
            spct.setSoLuong(req.getSoLuong());
            spct.setTrangThai(req.getTrangThai());
            spct.setDonGia(req.getDonGia());
            spct.setIdMauSac(req.getIdMauSac());
            spct.setIdSanPham(req.getIdSanPham());
            spct.setIdKichThuoc(req.getIdKichThuoc());
            return ResponseEntity.ok(spctRepo.save(spct));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Optional<SPCT> optional = spctRepo.findById(id);
        if (optional.isPresent()){
            SPCT spct = optional.get();
            spctRepo.delete(spct);
            return ResponseEntity.ok("Xóa OK");
        }else {
            return ResponseEntity.ok("Xóa thất bại");
        }
    }

}