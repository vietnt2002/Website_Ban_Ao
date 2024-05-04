package com.example.java4.controllers;

import com.example.java4.dto.hdct.StoreRequest;
import com.example.java4.entities.HDCT;
import com.example.java4.repositories.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class
HDCTController {
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

    public Timestamp StringsToTimeStampt(String date, String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(date + " " + time + ":00.000");
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            System.out.println("timestamp:" + timestamp);
            return timestamp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] TimeStampToStrings(Timestamp timeStampt) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(timeStampt);
            String[] parts = s.split(" ");
            return parts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getList")
    public List<HDCT> getList() {
        return hdctRepository.findAll();
    }

    @GetMapping("/detail/{id}")
    public HDCT detailHDCT(@PathVariable("id") Integer id) {
        return hdctRepository.findById(id).get();
    }

    @PostMapping("/add")
    public String addHDCT(@Valid @RequestBody StoreRequest request, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Thêm thất bại";
        } else {
            HDCT hdct = new HDCT();
            hdct.setIdHoaDon(request.getIdHoaDon());
            hdct.setIdSPCT(request.getIdSPCT());
            hdct.setSoLuong(request.getSoLuong());
            hdct.setDonGia(request.getDonGia());
            hdct.setThoiGian(request.getThoiGian());
            hdct.setTrangThai(request.getTrangThai());
            hdctRepository.save(hdct);
            return "Thêm thành công";
        }
    }

    @PutMapping("/update")
    public String updateHDCT(@Valid @RequestBody StoreRequest request, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Sửa thất bại";
        } else {
            HDCT hdct = new HDCT();
            hdct.setId(request.getId());
            hdct.setIdHoaDon(request.getIdHoaDon());
            hdct.setIdSPCT(request.getIdSPCT());
            hdct.setSoLuong(request.getSoLuong());
            hdct.setDonGia(request.getDonGia());
            hdct.setThoiGian(request.getThoiGian());
            hdct.setTrangThai(request.getTrangThai());
            hdctRepository.save(hdct);
            return "Sửa thành công";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHDCT(@PathVariable("id") Integer id) {
        hdctRepository.deleteById(id);
        return "Xóa thành công";
    }
}