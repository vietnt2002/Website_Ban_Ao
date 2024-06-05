package com.example.java4.controller.controller_tai;


import com.example.java4.entities.HoaDon;
import com.example.java4.repositories.HoaDonRepository;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.NhanVienRepository;
import com.example.java4.response.HoaDonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
        Pageable pageable = PageRequest.of(Integer.valueOf(pageParam), 5, Sort.by(Sort.Direction.DESC, "ngayTao"));

        // Lấy ra danh sách hóa đơn có trạng thái là 1(Đã thanh toán)
        Page<HoaDon> pageHD = _hoaDonRepo.findByTrangThai(1, pageable);
        // Chuyển Đổi từ Page<HoaDon> sang list<HoaDonDTO> để trả về view
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // Convert từ Page<HoaDon> sang list<HoaDonDTO>
        List<HoaDonDTO> listHoaDonDTO = pageHD.stream()
                .map(hoaDon -> new HoaDonDTO(
                        hoaDon.getId().toString(),
                        hoaDon.getMa(),
                        hoaDon.getIdNhanVien() != null ? hoaDon.getIdNhanVien().getHoTen() : null,
                        hoaDon.getIdKhachHang() != null ? hoaDon.getIdKhachHang().getHoTen() : null,
                        hoaDon.getIdKhachHang() != null ? hoaDon.getIdKhachHang().getSdt() : null,
                        hoaDon.getPhuongThucThanhToan(),
                        hoaDon.getTongTien(),
                        hoaDon.getNgayTao() != null ? hoaDon.getNgayTao().format(dateTimeFormatter) : null,
                        hoaDon.getTrangThai()))
                .collect(Collectors.toList());

        model.addAttribute("hoaDonPage", listHoaDonDTO);
        model.addAttribute("pageHD", pageHD);
        return "/view/view_tai/hoa_don/bill.jsp";

    }

    // Chức năng xem thông tin chi tiết hóa đơn theo IDHD
    @GetMapping("/detail/{idHD}")
    public String detailHDCT(Model model,
                             @PathVariable("idHD") String idHD){

        return "/view/view_tai/hoa_don/detail_bill.jsp";
    }
}
