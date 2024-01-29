package com.example.java4.controllers;
import com.example.java4.dto.san_pham.StoreRequest;
import com.example.java4.entities.SanPham;
import com.example.java4.repositories.SanPhamRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@Controller
@RequestMapping("san_pham")
public class SanPhamController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    StoreRequest rem;
    @Autowired
    SanPhamRepository spRepo;
    public SanPhamController() {
        rem = new StoreRequest();
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("data",rem);
        return "admin/ql_san_pham/Create";
    }

    @GetMapping("/index")
    public String index(Model model){
          model.addAttribute("data",spRepo.findAll());
          return "admin/ql_san_pham/Index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value ="id") SanPham sp){
        spRepo.delete(sp);
        return "redirect:/san_pham/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable(value ="id") SanPham sp){
        StoreRequest newReq = new StoreRequest();
        model.addAttribute("data",sp);
        return "admin/ql_san_pham/Edit";
    }

    @PostMapping ("/update/{id}")
    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req,BindingResult result,@PathVariable(value ="id") SanPham sp){
        if (result.hasErrors()) {
            return "admin/ql_san_pham/Edit";
        }
        else{
            sp.setTen(req.getTen());
            sp.setMa(req.getMa());
            sp.setTrangThai(req.getTrangThai());
            spRepo.save(sp);
            return  "redirect:/san_pham/index";
        }
    }

    @PostMapping("store")
    public String Store(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        SanPham sp = new SanPham();
        if (result.hasErrors()) {
            System.out.println("Có lỗi");
            return "admin/ql_san_pham/Create";
        }
        else{
            sp.setTen(req.getTen());
            sp.setMa(req.getMa());
            sp.setTrangThai(req.getTrangThai());
            spRepo.save(sp);
            return  "redirect:/san_pham/index";
        }
    }
}