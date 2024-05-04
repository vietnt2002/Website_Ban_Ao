package com.example.java4.rest.controller;

import com.example.java4.entities.KichThuoc;
import com.example.java4.entities.MauSac;
import com.example.java4.repositories.KichThuocRepository;
import com.example.java4.repositories.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kich-thuoc")
public class KichThuocRestController {

    @Autowired
    KichThuocRepository repository;


    @GetMapping("/get-all")
    public List<KichThuoc> getAll(){
        return repository.findAll();
    }




}
