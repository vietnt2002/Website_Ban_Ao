package com.example.java4.rest.controller;

import com.example.java4.entities.KichThuoc;
import com.example.java4.entities.NhanVien;
import com.example.java4.repositories.KichThuocRepository;
import com.example.java4.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nhan-vien")
public class NhanVienRestController {

    @Autowired
    NhanVienRepository repository;


    @GetMapping("/get-all")
    public List<NhanVien> getAll(){
        return repository.findAll();
    }




}
