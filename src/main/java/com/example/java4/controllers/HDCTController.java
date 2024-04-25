package com.example.java4.controllers;

import com.example.java4.dto.hdct.StoreRequest;
import com.example.java4.entities.HDCT;
import com.example.java4.entities.SPCT;
import com.example.java4.repositories.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("hdct")
public class HDCTController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    StoreRequest rem;
    @Autowired
    HoaDonRepository hdRepo;
    @Autowired
    HoaDonFullRepository hdFullRepo;
    @Autowired
    SPCTfullRepository spctFullRepository;
    @Autowired
    SPCTRepository spctRepository;
    @Autowired
    HDCTRepository hdctRepository;
    @Autowired
    HDCTfullRepository hdctFullRepository;

    public HDCTController() {
        rem = new StoreRequest();
    }
    public Timestamp StringsToTimeStampt(String date, String time){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(date +" "+time+":00.000");
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            System.out.println("timestamp:"+timestamp);
            return timestamp;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[]  TimeStampToStrings(Timestamp timeStampt){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(timeStampt);
            String[] parts = s.split(" ");
            return parts;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("data", rem);
        model.addAttribute("dsHoaDon", hdFullRepo.findAll());
        model.addAttribute("dsSPCT", spctFullRepository.findAll());
        return "admin/ql_hdct/Create";
    }

    @GetMapping("/index")
    public ResponseEntity<List<HDCT>> getIndex(Model model) {
        model.addAttribute("data", hdctFullRepository.findAll());
        return ResponseEntity.ok(hdctRepository.findAll());
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") HDCT hdct) {
        hdctRepository.delete(hdct);
        return "redirect:/spct/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable(value = "id") HDCT hdct) {
        model.addAttribute("dsHoaDon", hdFullRepo.findAll());
        model.addAttribute("dsSPCT", spctFullRepository.findAll());
        model.addAttribute("data", hdct);
        model.addAttribute("ngayMuaHang",TimeStampToStrings(hdct.getThoiGian())[0]);
        model.addAttribute("thoiGianMH",TimeStampToStrings(hdct.getThoiGian())[1]);
        return "admin/ql_hdct/Edit";
    }

    @PostMapping("/update/{id}")
    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req, @ModelAttribute("ngayMuaHang") String ngayMuaHang, @ModelAttribute("thoiGianMH") String thoiGianMH, BindingResult result, @PathVariable(value = "id") HDCT hdct, Model model) {
        if (result.hasErrors()) {
            return "admin/ql_hdct/Edit";
        } else {
            hdct.setIdHoaDon(Integer.parseInt(req.getIdHoaDon()));
            hdct.setIdSPCT(Integer.parseInt(req.getIdSPCT()));
            hdct.setSoLuong(req.getSoLuong());
            hdct.setDonGia(req.getDonGia());
            hdct.setThoiGian(StringsToTimeStampt(ngayMuaHang,thoiGianMH));
            hdct.setTrangThai(req.getTrangThai());
            hdctRepository.save(hdct);
            return "redirect:/hdct/index";
        }
    }

    @PostMapping("/store")
    public String Store(
            @Valid @ModelAttribute("data") StoreRequest req, @ModelAttribute("ngayMuaHang") String ngayMuaHang, @ModelAttribute("thoiGianMH") String thoiGianMH,
            BindingResult result, Model model
    ) {
        SPCT spct = new SPCT();
        HDCT hdct = new HDCT();
        if (result.hasErrors()) {
            model.addAttribute("dsHoaDon", hdFullRepo.findAll());
            model.addAttribute("dsSPCT", spctFullRepository.findAll());
            return "admin/ql_hdct/Create";
        } else {
            hdct.setIdHoaDon(Integer.parseInt(req.getIdHoaDon()));
            hdct.setIdSPCT(Integer.parseInt(req.getIdSPCT()));
            hdct.setSoLuong(req.getSoLuong());
            hdct.setDonGia(req.getDonGia());
            hdct.setThoiGian(StringsToTimeStampt(ngayMuaHang,thoiGianMH));
            hdct.setTrangThai(req.getTrangThai());
            hdctRepository.save(hdct);
            return "redirect:/hdct/index";
        }
    }
}