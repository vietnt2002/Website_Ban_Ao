package com.example.java4.controllers;

import com.example.java4.dto.hoaDon.StoreRequest;
import com.example.java4.entities.HoaDon;
import com.example.java4.repositories.HoaDonFullRepository;
import com.example.java4.repositories.HoaDonRepository;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.NhanVienRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HoaDonController {
    ArrayList<com.example.java4.dto.hoaDon.StoreRequest> ds;
    HoaDon hdRem = new HoaDon();
    StoreRequest rem = new StoreRequest();
    @Autowired
    private HoaDonRepository hdRepo;
    @Autowired
    private NhanVienRepository nvRepo;
    @Autowired
    private KhachHangRepository khRepo;
    @Autowired
    private HoaDonFullRepository hdFullRepo;

    public HoaDonController() {
        System.out.println("start new cycle");
        this.ds = new ArrayList<>();
    }

    @GetMapping("/getList")
    public List<HoaDon> getList() {
        return hdRepo.findAll();
    }

    @GetMapping("/detail/{id}")
    public HoaDon detailHoaDon(@PathVariable("id") Integer id) {
        return hdRepo.findById(id).get();
    }

    @PostMapping("/add")
    public String addHoaDon(@Valid @RequestBody StoreRequest request, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Thêm thất bại";
        } else {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setIdKH(request.getIdKH());
            hoaDon.setIdNV(request.getIdNV());
            hoaDon.setNgayMuaHang(request.getNgayMuaHang());
            hoaDon.setTrangThai(request.getTrangThai());
            hdRepo.save(hoaDon);
            return "Thêm thành công";
        }
    }

    @PutMapping("/update")
    public String updateHoaDon(@Valid @RequestBody StoreRequest request, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Sửa thất bại";
        } else {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(request.getId());
            hoaDon.setIdKH(request.getIdKH());
            hoaDon.setIdNV(request.getIdNV());
            hoaDon.setNgayMuaHang(request.getNgayMuaHang());
            hoaDon.setTrangThai(request.getTrangThai());
            hdRepo.save(hoaDon);
            return "Sửa thành công";
        }
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        hdRepo.deleteById(id);
        return "Xóa thành công";
    }
}