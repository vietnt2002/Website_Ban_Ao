package com.example.java4.controllers;
import com.example.java4.dto.san_pham.StoreRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@Controller
@RequestMapping("san_pham")
public class SanPhamController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    ArrayList<StoreRequest> ds;
    StoreRequest rem;
    public SanPhamController() {
        this.ds = new ArrayList<>();
        rem = new StoreRequest();
        ds.add(new StoreRequest("001","SP1","Wallet","Stocking"));
        ds.add(new StoreRequest("002","SP2","Learther strap apple watch","Stocking"));
        ds.add(new StoreRequest("003","SP3","Clutch","Stocking"));
        ds.add(new StoreRequest("004","SP4","Belt","Stocking"));
        ds.add(new StoreRequest("005","SP5","Leather case","Stocking"));
        ds.add(new StoreRequest("006","SP6","Sticky leather(optional size)","Stocking"));
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        model.addAttribute("data",rem);
        return "admin/ql_san_pham/Create";
    }

    @GetMapping("/index")
    public String index(Model model){
          model.addAttribute("data", ds);
          return "admin/ql_san_pham/Index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value ="id") String id){
        for (int i = 0; i <ds.size() ; i++) {
            if(ds.get(i).getId().equals(id)){
                ds.remove(i);
            }
        }
        return "redirect:/san_pham/index";
    }

    @GetMapping("/update/{id}")
    public String getUpdate(Model model, @PathVariable(value ="id") String id){
        StoreRequest newReq = new StoreRequest();
        for (int i = 0; i <ds.size() ; i++) {
            if(ds.get(i).getId().equals(id)){
                newReq = ds.get(i);
            }
        }
        model.addAttribute("data",newReq);
        return "admin/ql_san_pham/Edit";
    }

    @PostMapping ("/update/{id}")
    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req,BindingResult result,@PathVariable(value ="id") String id){
        if (result.hasErrors()) {
            return "admin/ql_san_pham/Edit";
        }
        else{
            for (int i = 0; i < ds.size(); i++) {
                if(ds.get(i).getId().equals(id)){
                    ds.get(i).setMa(req.getMa());
                    ds.get(i).setTen(req.getTen());
                    ds.get(i).setTrangThai(req.getTrangThai());
                }
            }
            rem = req;
            return  "redirect:/san_pham/index";
        }
    }

    @PostMapping("store")
    public String Store(
            @Valid @ModelAttribute("data") StoreRequest req,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            System.out.println("Có lỗi");
            return "admin/ql_san_pham/Create";
        }
        else{
            rem = req;
            ds.add(req);
            return  "redirect:/san_pham/index";
        }
    }
}