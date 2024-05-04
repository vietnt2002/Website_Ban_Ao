package com.example.java4.controllers;
import com.example.java4.dto.kich_thuoc.StoreRequest;
import com.example.java4.entities.KichThuoc;
import com.example.java4.entities.MauSac;
import com.example.java4.repositories.KichThuocRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kich_thuoc")
public class KichThuocController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    StoreRequest rem;
    StoreRequest remUpdate;


    @Autowired
    KichThuocRepository ktRepo;
    public KichThuocController() {
        this.rem = new StoreRequest();
        this.remUpdate = new StoreRequest();
    }


    //    Lấy tất cả các dữ liệu danh sách kích thước
    @GetMapping("/get-all")
    public List<KichThuoc> index(Model model) {
        return ktRepo.findAll();
    }

    // Lấy dữ liệu đối tượng Kích Thước theo Id
    @GetMapping("/detail/{id}")
    public KichThuoc detail(@PathVariable("id") Integer id, Model model) {
        return ktRepo.findById(id).orElse(null);
    }

    //  Lấy danh sách Kích Thước  có phân trang, 5 phần tử trên 1 trang
    @GetMapping("/phan-trang")
    public List<KichThuoc> page(@RequestParam(value = "page", defaultValue = "0") Integer pageNumber, Model model) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        return ktRepo.findByTrangThai(1, pageable).getContent();
    }




    //    Chức năng thêm mới Kích Thước
    @PostMapping("/create")
    public String create(
            @RequestBody @Valid StoreRequest kichThuocRequest,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Thêm mới kích thước thất bại";
        } else {
            KichThuoc kichThuoc = new KichThuoc();
            kichThuoc.setTen(kichThuocRequest.getTen());
            kichThuoc.setMa(kichThuocRequest.getMa());
            kichThuoc.setTrangThai(kichThuocRequest.getTrangThai());
            ktRepo.save(kichThuoc);
            return "Thêm mới  kích thước thành công";
        }
    }


    //    Chức năng cập nhật Kích Thước
    @PutMapping("/update-kichthuoc/{id}")
    public String doUpdate(
            @RequestBody @Valid StoreRequest kichThuocRequest ,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
            return "Sửa kích thước thất bại";
        } else {

            KichThuoc kichThuoc = new KichThuoc();
            System.out.println("update post check id:" + kichThuoc.getId());
            kichThuoc.setId(kichThuocRequest.getId());
            kichThuoc.setTen(kichThuocRequest.getTen());
            kichThuoc.setMa(kichThuocRequest.getMa());
            kichThuoc.setTrangThai(kichThuocRequest.getTrangThai());
            ktRepo.save(kichThuoc);
            return "Sửa kích thước thành công";
        }
    }

    //    Chức năng xóa Kích Thước
    @DeleteMapping("/delete-kichthuoc")
    public String delete(@PathVariable(value = "id") KichThuoc kichThuoc) {

        if (kichThuoc == null) {
            return "Xóa kích thước thất bại";
        }

        ktRepo.delete(kichThuoc);
        return "Xóa kích thước thành công ";
    }

}