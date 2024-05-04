package com.example.java4.rest.controller;

import com.example.java4.entities.MauSac;
import com.example.java4.repositories.MauSacRepository;
import com.example.java4.response.MauSacResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mau-sac")
public class MauSacRestController {

    @Autowired
    MauSacRepository repository;


    @GetMapping("/get-all")
    public List<MauSac> getAll(){
        return repository.findAll();
    }




}
