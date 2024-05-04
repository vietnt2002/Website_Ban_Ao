package com.example.java4.controllers;

import com.example.java4.dto.khach_hang.StoreRequest;
import com.example.java4.entities.KhachHang;
import com.example.java4.repositories.KhachHangRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KhachHangController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    List<KhachHang> ds;
    StoreRequest rem;
    @Autowired
    KhachHangRepository khRepo;

    public KhachHangController() {
        this.rem = new StoreRequest();
    }

    @GetMapping("/getList")
    public List<KhachHang> getList() {
        return khRepo.findAll();
    }

    @GetMapping("/detail/{id}")
    public KhachHang detailKhachHang(@PathVariable("id") Integer id) {
        return khRepo.findById(id).get();
    }

    @PostMapping("/add")
    public String addKhachHang(@Valid @RequestBody StoreRequest request, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Thêm thất bại";
        } else {
            KhachHang kh = new KhachHang();
            kh.setMa(request.getMa());
            kh.setTen(request.getTen());
            kh.setSdt(request.getSdt());
            kh.setTrangThai(request.getTrangThai());
            khRepo.save(kh);
            return "Thêm thành công";
        }
    }

    @PutMapping("/update")
    public String updateKhachHang(@Valid @RequestBody StoreRequest request, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Sửa thất bại";
        } else {
            KhachHang kh = new KhachHang();
            kh.setId(request.getId());
            kh.setMa(request.getMa());
            kh.setTen(request.getTen());
            kh.setSdt(request.getSdt());
            kh.setTrangThai(request.getTrangThai());
            khRepo.save(kh);
            return "Sửa thành công";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteKhachHang(@PathVariable("id") Integer id) {
        khRepo.deleteById(id);
        return "Xóa thành công";
    }
}