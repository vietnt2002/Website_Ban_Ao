package com.example.java4.restcontrollers;

import com.example.java4.entities.SanPhamChiTiet;
import com.example.java4.repositories.SPCTRepository;
import com.example.java4.response.SanPhamChiTietResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("san-pham-chi-tiet ")
public class SPCTController {

    @Autowired
    SPCTRepository spctRepo;

    @GetMapping("/index")
    public List<SanPhamChiTietResponse> index() {
        return spctRepo.getList();
    }

    @GetMapping("/detail")
    public SanPhamChiTiet detail(@RequestParam("id") Integer id) {
        return spctRepo.findById(id).get();
    }

}