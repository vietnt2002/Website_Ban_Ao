package com.example.java4.controller.controller_tai;


import com.example.java4.entities.HoaDon;
import com.example.java4.repositories.HoaDonRepository;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hoa-don")
public class QuanLyHoaDonController {

    @Autowired
    HoaDonRepository _hoaDonRepo;

    @Autowired
    KhachHangRepository _khachHangRepo;

    @Autowired
    NhanVienRepository _nhanVienRepo;


    // Hiển thị giao diện quản lý hóa đơn
    @GetMapping("/hien-thi")
    public String view(Model model, @RequestParam(value = "page", defaultValue = "0") String pageParam) {
        // Lấy ra 5 hóa đơn trong 1 Page
        Pageable pageable = PageRequest.of(Integer.valueOf(pageParam), 5);

        // Lấy ra danh sách hóa đơn có trạng thái là 1(Đã thanh toán)
        Page<HoaDon> pageHD = _hoaDonRepo.findByTrangThai(1, pageable);
        model.addAttribute("pageHD", pageHD);
        return "/view/view_tai/hoa_don/bill.jsp";

    }
}
