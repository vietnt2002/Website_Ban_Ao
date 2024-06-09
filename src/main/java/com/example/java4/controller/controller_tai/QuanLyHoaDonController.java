package com.example.java4.controller.controller_tai;


import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.HoaDon;
import com.example.java4.repositories.HDCTRepository;
import com.example.java4.repositories.HoaDonRepository;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.NhanVienRepository;
import com.example.java4.repositories.repo_tai.IHoaDonRepository;
import com.example.java4.response.HoaDonChiTietDTO;
import com.example.java4.response.HoaDonDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    IHoaDonRepository _hoaDonRepository;



    // Hiển thị giao diện quản lý hóa đơn
    @GetMapping("/hien-thi")
    public String view(Model model, @RequestParam(value = "page", defaultValue = "0") String pageParam) {
        // Lấy ra 5 hóa đơn trong 1 Page
        Pageable pageable = PageRequest.of(Integer.valueOf(pageParam), 5, Sort.by(Sort.Direction.DESC, "ngayTao"));

        // Lấy ra danh sách hóa đơn có trạng thái là 1(Đã thanh toán)
        Page<HoaDon> pageHD = _hoaDonRepo.findByTrangThai(_hoaDonRepository.DA_HOAN_THANH, pageable);
        Page<HoaDon> pageHDAll = _hoaDonRepo.findAll(pageable);
        // Chuyển Đổi từ Page<HoaDon> sang list<HoaDonDTO> để trả về view
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        // Convert từ Page<HoaDon> sang list<HoaDonDTO>
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
                        hoaDon.getTrangThai()))
                .collect(Collectors.toList());

        List<HoaDonDTO> listHoaDonALLDTO = pageHDAll.stream()
                .map(hoaDon -> new HoaDonDTO(
                        hoaDon.getId().toString(),
                        hoaDon.getMa(),
                        hoaDon.getIdKhachHang(),
                        hoaDon.getIdNhanVien(),
                        hoaDon.getPhuongThucThanhToan(),
                        hoaDon.getTongTien(),
                        hoaDon.getLoaiHoaDon(),
                        hoaDon.getNgayTao() != null ? hoaDon.getNgayTao().format(dateTimeFormatter) : null,
                        hoaDon.getTrangThai()))
                .collect(Collectors.toList());

        model.addAttribute("hoaDonPage", listHoaDonDTO);
        model.addAttribute("pageHD", pageHD);
        model.addAttribute("pageHDALL", pageHDAll);
        model.addAttribute("hoaDonPageAll", listHoaDonALLDTO);
        model.addAttribute("hoaDonPageXacNhan",pageHD.getContent());
        return "/view/view_tai/hoa_don/bill.jsp";

    }

    // Chức năng xem thông tin chi tiết hóa đơn theo IDHD
    @GetMapping("/detail/{idHD}")
    public String detailHDCT(Model model,
                             @PathVariable("idHD") String idHD) {

        //Convert String sang UUId
        try {
            UUID uuid = UUID.fromString(idHD); // This will throw an exception if the format is incorrect
        } catch (IllegalArgumentException e) {
            // Handle the invalid UUID format
            model.addAttribute("errorMessage", "ID hóa đơn không hợp lệ.");
            return "/view/view_tai/hoa_don/detail_bill.jsp";
        }

        // Lấy danh sách chi tiết hóa đơn
        List<ChiTietHoaDon> listHDCT = _hoaDonChiTietRepo.findAllByHoaDon_Id(idHD);
        // Lấy ra hóa đơn theo ID
        HoaDon hoaDon = _hoaDonRepo.findById(idHD).orElse(null);

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
                hoaDon.getTrangThai()
        );


        // Thêm các thông tin vào model để truyền sang JSP
        model.addAttribute("hoaDonDTO", hoaDonDTO);
        model.addAttribute("listHDCT", listHDCT);

        return "/view/view_tai/hoa_don/detail_bill.jsp";
    }

    // Lọc hóa đơn theo loại hóa đơn
    @GetMapping("/filter")
    public String filterHoaDon(Model model, @RequestParam("loaiHoaDon") int loaiHoaDon, @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "ngayTao"));
        Page<HoaDon> pageHD = _hoaDonRepository.findByLoaiHoaDon(loaiHoaDon, pageable);

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
                        hoaDon.getTrangThai()))
                .collect(Collectors.toList());
        model.addAttribute("hoaDonPageAll", listHoaDonDTO);
        model.addAttribute("pageHDALL", pageHD);
        model.addAttribute("loaiHoaDon", loaiHoaDon);
        return "/view/view_tai/hoa_don/bill.jsp";
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

        Page<HoaDon> pageHD = _hoaDonRepository.searchByKeyword(keyword,pageable);

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


    // Chức năng in phiếu HoaDon ra file PDF
//    @GetMapping(value = "/in-hoa-don/pdf/{idHD}")
//    public void generatePdf(@PathVariable("idHD") String idHD, HttpServletResponse response) throws DocumentException, IOException {
////       Lấy ra thông tin hóa đơn và hóa đơn chi tiết
//        HoaDon hoaDon = _hoaDonRepo.findById(idHD).orElse(null);
//        List<ChiTietHoaDon> listHDCT = _hoaDonChiTietRepo.findAllByHoaDon_Id(idHD);
//
//        if (hoaDon == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invoice not found");
//            return;
//        }
//
//        // Tạo 1 tài liệu mới
//        Document document = new Document();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        PdfWriter.getInstance(document, out);
//
//        document.open();
//        // Thêm nội dung vào tài liệu
//        // Thêm nội dung vào tài liệu
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        document.add(new Paragraph("ID hóa đơn: " + hoaDon.getId()));
//        document.add(new Paragraph("Mã hóa đơn: " + hoaDon.getMa()));
//        document.add(new Paragraph("Họ tên khách hàng: " + hoaDon.getIdKhachHang().getHoTen()));
//        document.add(new Paragraph("Tổng tiền: " + hoaDon.getTongTien()));
//        document.add(new Paragraph("Ngày tạo: " + hoaDon.getNgayTao().format(formatter)));
//        document.add(new Paragraph("Chi tiết:"));
//
//        for (ChiTietHoaDon chiTiet : listHDCT) {
//            document.add(new Paragraph(" - " + chiTiet.getIdCTSP().getIdSanPham().getTen() + ": " + chiTiet.getDonGia()));
//        }
//
//        document.close();
//
//        // Đặt các headers của response
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=invoice.pdf");
//        response.setContentLength(out.size());
//
//        // Ghi PDF vào response output stream
//        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
//        response.getOutputStream().write(in.readAllBytes());
//    }


}
