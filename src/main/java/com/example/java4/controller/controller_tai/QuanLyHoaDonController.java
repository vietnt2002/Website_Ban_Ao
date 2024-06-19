package com.example.java4.controller.controller_tai;


import com.example.java4.config.HoaDonUtil;
import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.DiaChi;
import com.example.java4.entities.HoaDon;
import com.example.java4.repositories.HDCTRepository;
import com.example.java4.repositories.HoaDonRepository;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.NhanVienRepository;
import com.example.java4.repositories.repo_tai.IDiaChiRepository;
import com.example.java4.repositories.repo_tai.IHoaDonRepository;
import com.example.java4.response.HoaDonChiTietDTO;
import com.example.java4.response.HoaDonDTO;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
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

    @Autowired
    HDCTRepository _hoaDonChiTietRepo;

    @Autowired
    IHoaDonRepository hoaDonRepository;

    @Autowired
    IDiaChiRepository _diaChiRepository;




    @GetMapping("/hien-thi")
    public String view(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "status", required = false) String status,
                       @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "startDate", required = false) String startDateStr,
                       @RequestParam(value = "endDate", required = false) String endDateStr) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "ngayTao"));


        //Lớp Util để xử chuyển đổi trạng thái
        HoaDonUtil hoaDonUtil = new HoaDonUtil();

        // Xử lý ngày tạo
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        if (startDateStr != null && !startDateStr.isEmpty()) {
            startDate = LocalDate.parse(startDateStr).atStartOfDay();
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            endDate = LocalDate.parse(endDateStr).atTime(LocalTime.MAX);
        }

        Page<HoaDon> pageHD;
        //Tìm kiếm theo từ khóa
        if (keyword != null && !keyword.isEmpty()) {
            pageHD = hoaDonRepository.searchByKeywordAndDate(keyword, startDate, endDate, pageable);
        }
        // Tìm kiếm theo ngày tạo
//        else if(ngayTao != null){
//            pageHD = hoaDonRepository.findByNgayTaoBetween(startOfDay, endOfDay, pageable);
//        }
        else {
            if (status != null && !status.isEmpty()) {
                switch (status) {
                    case "all":
                        pageHD = (loaiHoaDon != null) ? hoaDonRepository.findByLoaiHoaDon(loaiHoaDon, pageable) : hoaDonRepository.findAll(pageable);
                        break;
                    case "confirmation":
                        pageHD = (loaiHoaDon != null) ? hoaDonRepository.findByTrangThaiAndLoaiHoaDon(IHoaDonRepository.CHO_XAC_NHAN, loaiHoaDon, pageable) : hoaDonRepository.findByTrangThai(IHoaDonRepository.CHO_XAC_NHAN, pageable);
                        break;
                    case "confirmed":
                        pageHD = (loaiHoaDon != null) ? hoaDonRepository.findByTrangThaiAndLoaiHoaDon(IHoaDonRepository.DA_XAC_NHAN, loaiHoaDon, pageable) : hoaDonRepository.findByTrangThai(IHoaDonRepository.DA_XAC_NHAN, pageable);
                        break;
                    case "delivery":
                        pageHD = (loaiHoaDon != null) ? hoaDonRepository.findByTrangThaiAndLoaiHoaDon(IHoaDonRepository.CHO_GIAO_HANG, loaiHoaDon, pageable) : hoaDonRepository.findByTrangThai(IHoaDonRepository.CHO_GIAO_HANG, pageable);
                        break;
                    case "delivered":
                        pageHD = (loaiHoaDon != null) ? hoaDonRepository.findByTrangThaiAndLoaiHoaDon(IHoaDonRepository.DANG_GIAO_HANG, loaiHoaDon, pageable) : hoaDonRepository.findByTrangThai(IHoaDonRepository.DANG_GIAO_HANG, pageable);
                        break;
                    case "accomplished":
                        pageHD = (loaiHoaDon != null) ? hoaDonRepository.findByTrangThaiAndLoaiHoaDon(IHoaDonRepository.DA_HOAN_THANH, loaiHoaDon, pageable) : hoaDonRepository.findByTrangThai(IHoaDonRepository.DA_HOAN_THANH, pageable);
                        break;
                    case "cancelled":
                        pageHD = (loaiHoaDon != null) ? hoaDonRepository.findByTrangThaiAndLoaiHoaDon(IHoaDonRepository.DA_HUY, loaiHoaDon, pageable) : hoaDonRepository.findByTrangThai(IHoaDonRepository.DA_HUY, pageable);
                        break;
                    default:
                        pageHD = (loaiHoaDon != null) ? hoaDonRepository.findByLoaiHoaDon(loaiHoaDon, pageable) : hoaDonRepository.findAll(pageable);
                        break;
                }
            } else {
                pageHD = (loaiHoaDon != null && loaiHoaDon != -1) ? hoaDonRepository.findByLoaiHoaDon(loaiHoaDon, pageable) : hoaDonRepository.findAll(pageable);
            }
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<HoaDonDTO> listHoaDonDTO = pageHD.stream()
                .map(hoaDon -> {
                    HoaDonDTO hd = new HoaDonDTO(
                            hoaDon.getId().toString(),
                            hoaDon.getMa(),
                            hoaDon.getIdKhachHang(),
                            hoaDon.getIdNhanVien(),
                            hoaDon.getPhuongThucThanhToan(),
                            hoaDon.getTongTien(),
                            hoaDon.getLoaiHoaDon(),
                            hoaDon.getNgayTao() != null ? hoaDon.getNgayTao().format(dateTimeFormatter) : null,
                            hoaDon.getNgayThanhToan() != null ? hoaDon.getNgayThanhToan().format(dateTimeFormatter) : null,
                            hoaDon.getTrangThai()
                        );
                    hd.setTrangThaiText(hoaDonUtil.getTrangThaiName(hoaDon.getTrangThai()));
                    hd.setMaMau(hoaDonUtil.getStatusClass(hoaDon.getTrangThai()));
                    return hd;
                })

                .collect(Collectors.toList());

        // Đếm số lượng các hóa đơn theo trạng thái
        int countAll = hoaDonRepository.countAll();
        int countChoXacNhan = hoaDonRepository.countByTrangThai(IHoaDonRepository.CHO_XAC_NHAN);
        int countDaXacNhan = hoaDonRepository.countByTrangThai(IHoaDonRepository.DA_XAC_NHAN);
        int countChoGiaoHang = hoaDonRepository.countByTrangThai(IHoaDonRepository.CHO_GIAO_HANG);
        int countDangGiaoHang = hoaDonRepository.countByTrangThai(IHoaDonRepository.DANG_GIAO_HANG);
        int countDaHoanThanh = hoaDonRepository.countByTrangThai(IHoaDonRepository.DA_HOAN_THANH);
        int countDaHuy = hoaDonRepository.countByTrangThai(IHoaDonRepository.DA_HUY);

        // Đưa các số lượng này vào model
        model.addAttribute("countAll", countAll);
        model.addAttribute("countChoXacNhan", countChoXacNhan);
        model.addAttribute("countDaXacNhan", countDaXacNhan);
        model.addAttribute("countChoGiaoHang", countChoGiaoHang);
        model.addAttribute("countDangGiaoHang", countDangGiaoHang);
        model.addAttribute("countDaHoanThanh", countDaHoanThanh);
        model.addAttribute("countDaHuy", countDaHuy);




        model.addAttribute("hoaDonPage", listHoaDonDTO);
        model.addAttribute("pageHD", pageHD);
        model.addAttribute("currentStatus", status);
        model.addAttribute("currentLoaiHoaDon", loaiHoaDon);
        model.addAttribute("keyword", keyword);
        model.addAttribute("startDate", startDateStr);
        model.addAttribute("endDate", endDateStr);
//        model.addAttribute("ngayTao", ngayTaoStr);

        return "/view/view_tai/hoa_don/bill.jsp";
    }



    // Chức năng xem thông tin chi tiết hóa đơn theo IDHD
    @GetMapping("/detail/{idHD}")
    public String detailHDCT(Model model,
                             @PathVariable("idHD") String idHD) {

        //Convert String sang UUId
        try {
            UUID uuid = UUID.fromString(idHD);
        } catch (IllegalArgumentException e) {
            // Handle the invalid UUID format
            model.addAttribute("errorMessage", "ID hóa đơn không hợp lệ.");
            return "/view/view_tai/hoa_don/detail_bill.jsp";
        }

        // Lấy danh sách chi tiết hóa đơn
        List<ChiTietHoaDon> listHDCT = _hoaDonChiTietRepo.findAllByHoaDon_Id(idHD);
        // Lấy ra hóa đơn theo ID
        HoaDon hoaDon = _hoaDonRepo.findById(idHD).orElse(null);
        DiaChi diaChiKhachHang = _diaChiRepository.findByIdKhachHang_Id(hoaDon.getIdKhachHang().getId());
        if(diaChiKhachHang == null){
            model.addAttribute("errorMessage", "Khách hàng chưa có địa chỉ");
            return "/view/view_tai/hoa_don/detail_bill.jsp";
        }

        if (hoaDon == null) {
            // Xử lý trường hợp không tìm thấy hóa đơn
            model.addAttribute("errorMessage", "Không tìm thấy hóa đơn.");
            return "/view/view_tai/hoa_don/detail_bill.jsp";
        }

        // Chuyển đổi từ HoaDon sang HoaDonDTO
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        HoaDonDTO hoaDonDTO = new HoaDonDTO(
                hoaDon.getId().toString(),
                hoaDon.getMa(),
                hoaDon.getIdKhachHang(),
                hoaDon.getIdNhanVien(),
                hoaDon.getPhuongThucThanhToan(),
                hoaDon.getTongTien(),
                hoaDon.getLoaiHoaDon(),
                hoaDon.getNgayTao() != null ? hoaDon.getNgayTao().format(dateTimeFormatter) : null,
                hoaDon.getNgayThanhToan() != null ? hoaDon.getNgayThanhToan().format(dateTimeFormatter) : null,
                hoaDon.getTrangThai()
        );


        // Thêm các thông tin vào model để truyền sang JSP
        model.addAttribute("hoaDonDTO", hoaDonDTO);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("diaChiKhachHang", diaChiKhachHang);


        switch (hoaDon.getTrangThai()) {
            case IHoaDonRepository.CHO_XAC_NHAN:
                model.addAttribute("step", "confirmation");
                break;
            case IHoaDonRepository.DA_XAC_NHAN:
                model.addAttribute("step", "confirmed");
                break;
            case IHoaDonRepository.CHO_GIAO_HANG:
                model.addAttribute("step", "delivery");
                break;
            case IHoaDonRepository.DANG_GIAO_HANG:
                model.addAttribute("step", "delivered");
                break;
            case IHoaDonRepository.DA_HOAN_THANH:
                model.addAttribute("step", "accomplished");
                break;
            default:
                model.addAttribute("step", "default");
                break;
        }



        return "/view/view_tai/hoa_don/detail_bill.jsp";
    }




    // Chức năng tìm kiếm hóa đơn
    @GetMapping("/tim-kiem")
    public String searchHoaDon(Model model,
                               @RequestParam(value = "keyword", required = false) String keyword,
//                               @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
//                               @RequestParam(value = "ngayTao", required = false) String ngayTaoStr,
                               @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "ngayTao"));

//        LocalDate ngayTao = null;
//        if (ngayTaoStr != null && !ngayTaoStr.isEmpty()) {
//            ngayTao = LocalDate.parse(ngayTaoStr);
//        }

        Page<HoaDon> pageHD = hoaDonRepository.searchByKeyword(keyword,pageable);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<HoaDonDTO> listHoaDonDTO = pageHD.stream()
                .map(hoaDon -> new HoaDonDTO(
                        hoaDon.getId().toString(),
                        hoaDon.getMa(),
                        hoaDon.getIdKhachHang(),
                        hoaDon.getIdNhanVien(),
                        hoaDon.getPhuongThucThanhToan(),
                        hoaDon.getTongTien(),
                        hoaDon.getLoaiHoaDon(),
                        hoaDon.getNgayTao() != null ? hoaDon.getNgayTao().format(dateTimeFormatter) : null,
                        hoaDon.getNgayThanhToan() != null ? hoaDon.getNgayThanhToan().format(dateTimeFormatter) : null,
                        hoaDon.getTrangThai()))
                .collect(Collectors.toList());

        System.out.println(keyword);

        model.addAttribute("hoaDonPageAll", listHoaDonDTO);
        model.addAttribute("pageHDALL", pageHD);
        model.addAttribute("keyword", keyword);
//        model.addAttribute("loaiHoaDon", loaiHoaDon);
//        model.addAttribute("ngayTao", ngayTaoStr);
        return "/view/view_tai/hoa_don/bill.jsp";
    }







    // Chức năng xác nhận đơn hàng
    @PostMapping("/xac-nhan/{idHD}")
    public String confirmBill(@PathVariable("idHD") String idHD,
                              @RequestParam("trangThai") int trangThai,
                              @RequestParam("moTa") String moTa,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        try {
           HoaDon hoaDon = _hoaDonRepo.findById(idHD).orElse(null);


            if (hoaDon == null) {
                model.addAttribute("errorMessage", "Không tìm thấy hóa đơn.");
                redirectAttributes.addAttribute("confirmError", "Không tìm thấy hóa đơn.");
                return "redirect:/hoa-don/detail/" + idHD;
            }

            HoaDonDTO hoaDonDTO = HoaDonDTO.fromEntity(hoaDon);

            // Cập nhật trạng thái và mô tả
//            hoaDon.setTrangThai(trangThai);
//            hoaDon.setGhiChu(moTa); // Assuming the description is stored in `ghiChu`
//            hoaDon.setNgayCapNhat(LocalDateTime.now()); // Update the last update date
//            hoaDonRepo.save(hoaDon);

            switch (hoaDon.getTrangThai()) {
                case IHoaDonRepository.CHO_XAC_NHAN:
                    hoaDon.setTrangThai(IHoaDonRepository.DA_XAC_NHAN);
//                    hoaDon.set("Đã xác nhận: " + moTa);
                    hoaDon.setNgayTao(LocalDateTime.now());
                    break;
                case IHoaDonRepository.DA_XAC_NHAN:
                    hoaDon.setTrangThai(IHoaDonRepository.CHO_GIAO_HANG);
                    hoaDon.setNgayTao(LocalDateTime.now());
                    break;
                case IHoaDonRepository.CHO_GIAO_HANG:
                    hoaDon.setTrangThai(IHoaDonRepository.DANG_GIAO_HANG);
                    hoaDon.setNgayTao(LocalDateTime.now());
                    break;
                case IHoaDonRepository.DANG_GIAO_HANG:
                    hoaDon.setTrangThai(IHoaDonRepository.DA_HOAN_THANH);
                    hoaDon.setNgayTao(LocalDateTime.now());
                    break;
                default:
                    model.addAttribute("errorMessage", "Trạng thái không hợp lệ.");
                    return "redirect:/hoa-don/detail/" + idHD;
            }

            // Lưu lại thay đổi
            _hoaDonRepo.save(hoaDon);
            // Chuyển đổi HoaDon sang HoaDonDTO sau khi cập nhật và thêm vào model
            hoaDonDTO = HoaDonDTO.fromEntity(hoaDon);
            model.addAttribute("hoaDonDTO", hoaDonDTO);
            redirectAttributes.addAttribute("hoaDonDTO", hoaDonDTO);


            redirectAttributes.addAttribute("confirmSuccess", "Cập nhật trạng thái đơn hàng thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("confirmError", "Có lỗi xảy ra khi cập nhật trạng thái đơn hàng.");
        }
        return "redirect:/hoa-don/detail/" + idHD;
    }




}
