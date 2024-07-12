package com.example.java4.controller.controller_doi_hang;

import com.example.java4.entities.HoaDon;
import com.example.java4.repositories.viet_repo.CTHDRepository;
import com.example.java4.repositories.viet_repo.HDRepository;
import com.example.java4.response.CTHDResponse;
import com.example.java4.response.HDResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin")
public class DoiHangController {
    @Autowired
    HDRepository hoaDonRepo;
    @Autowired
    CTHDRepository cthdRepo;

    @GetMapping("doi-hang-view")
    public String doiHangView(){
        return "/view/view_doi_hang/doiHang.jsp";
    }

    @PostMapping("tim-kiem-hoa-don")
    public String timKiemHoaDon(
            @RequestParam("maHD") String maHD,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        HoaDon hoaDon = hoaDonRepo.findByMaHD(maHD);
        if (hoaDon == null){
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy hóa đơn "+maHD);
            return "redirect:/admin/doi-hang-view";
        }else {
            return "redirect:/admin/hoa-don-doi-hang/"+hoaDon.getId();
        }
    }

    @GetMapping("hoa-don-doi-hang/{idHD}")
    public String hoaDonDoiHang(
            @PathVariable("idHD") String idHD,
            Model model
    ){

        HDResponse hoaDon = hoaDonRepo.getHoaDonByIdHD(idHD);
        model.addAttribute("hoaDon", hoaDon);

        List<CTHDResponse> listCTHDByIdHD = cthdRepo.getAllCTHDByIdHD(idHD);
        model.addAttribute("listCTHD", listCTHDByIdHD);
        return "/view/view_doi_hang/hoaDonDoiHang.jsp";
    }
}
