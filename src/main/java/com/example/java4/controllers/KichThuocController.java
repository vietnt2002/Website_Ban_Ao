package com.example.java4.controllers;
import com.example.java4.dto.kich_thuoc.StoreRequest;
import com.example.java4.entities.KichThuoc;
import com.example.java4.repositories.KichThuocRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@Controller
@RequestMapping("kich_thuoc")
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
    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("data",rem);
        return "admin/ql_kich_thuoc/Create";
    }

    @GetMapping("/index")
        public String getIndexPage(Model model){
        model.addAttribute("data",ktRepo.findAll());
        return "admin/ql_kich_thuoc/Index";
        }
    @GetMapping("/delete/{id}")
        public String delete(Model model, @PathVariable(value="id") KichThuoc kt){
        ktRepo.delete(kt);
        return "redirect:/kich_thuoc/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model,@PathVariable(value = "id") KichThuoc kt){
        model.addAttribute("data",kt);
        return "admin/ql_kich_thuoc/Edit";
    }

    @PostMapping("/update/{id}")
    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req, BindingResult rs, @PathVariable(value="id")KichThuoc kt){
        if(rs.hasErrors()){
             return "admin/ql_kich_thuoc/Edit";
        }
        else {
            kt.setMa(req.getMa());
            kt.setTen(req.getTen());
            kt.setTrangThai(req.getTrangThai());
            ktRepo.save(kt);
            return "redirect:/kich_thuoc/index";
        }
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        KichThuoc kt = new KichThuoc();
        rem = req;
        if(result.hasErrors()){
            return "admin/ql_kich_thuoc/Create";
        }
        else{
            kt.setMa(req.getMa());
            kt.setTen(req.getTen());
            kt.setTrangThai(req.getTrangThai());
            ktRepo.save(kt);
            return "redirect:/kich_thuoc/index";
        }
    }
}