package com.example.java4.controller.controller_viet;

import com.example.java4.entities.SanPham;
import com.example.java4.repositories.SanPhamRepository;
import com.example.java4.request.req_viet.SanPhamRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("store")
public class Controller_V {
    @Autowired
    SanPhamRepository sanPhamRepo;

    @GetMapping("/trang-chu")
    public String getTrangChu(){
        return "/view/view_viet/trangChu.jsp";
    }

    @GetMapping("/detail")
    public String detail(){
        return "/view/view_viet/chiTietSanPham.jsp";
    }

    @GetMapping("/san-pham")
    public String sanPham(Model model){
        SanPhamRequest sanPhamRequest = new SanPhamRequest();
        model.addAttribute("sanPham", sanPhamRequest);
        model.addAttribute("listSP", sanPhamRepo.findAll());
        return "/view/view_viet/sanPham.jsp";
    }

    @PostMapping("add")
    public String add(
            Model model,
            @Valid @ModelAttribute("sanPham") SanPhamRequest spReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "/view/view_viet/sanPham.jsp";
        }
        SanPham sanPham = new SanPham();
        sanPham.setTen(spReq.getTen());
        sanPham.setTrangThai(spReq.getTrangThai());
        sanPham.setNgayTao(spReq.getNgayTao());
        sanPhamRepo.save(sanPham);

        return "/view/view_viet/sanPham.jsp";
    }

}
