
package com.example.java4.controller.controller_tai;


import com.example.java4.config.HoaDonUtil;
import com.example.java4.config.UserInfor;
import com.example.java4.entities.*;
import com.example.java4.entities.tai.HoaDon_Tai;
import com.example.java4.repositories.*;
import com.example.java4.repositories.repo_tai.*;
import com.example.java4.request.Areq_fixed.DiaChiRequest;
import com.example.java4.request.req_tai.DiaChiDTO;
import com.example.java4.request.req_viet.NhanVienRequest;
import com.example.java4.response.GiaoHangDTO;
import com.example.java4.response.HoaDonChiTietDTO;
import com.example.java4.response.HoaDonDTO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/hoa-don")
public class QuanLyHoaDonController {

//    @Autowired
//    HoaDonRepository _hoaDonRepo;

    @Autowired
    KhachHangRepository _khachHangRepo;

    @Autowired
    NhanVienRepository _nhanVienRepo;

    @Autowired
    HDCTRepository _hoaDonChiTietRepo;

    @Autowired
    IHoaDonRepository hoaDonRepository;

    @Autowired
    IHoaDonChiTietReposioty _hoaDonChiTietReposioty;

    @Autowired
    IDiaChiRepository _diaChiRepository;

    @Autowired
    IHoaDonRepo _hoaDonRepoTai;


    @Autowired
    SPCTRepository _sanPhamChiTietRepo;


    @Autowired
    KichThuocRepository kichThuocRepo;

    @Autowired
    ChatLieuRepository chatLieuRepo;

    @Autowired
    KieuTayRepository kieuTayRepo;

    @Autowired
    NhanVienRepository nhanVienRepo;

    @Autowired
    KhuyenMaiRepository khuyenMaiRepo;

    @Autowired
    MauSacRepository mauSacRepository;

    @Autowired
    IGiaoHangRepository _giaoHangRepo;

    @Autowired
    IChiTietSanPhamRepository _chiTietSanPhamRepo;

    @Autowired
    IHinhAnhRepository _hinhAnhRepo;


    private final RestTemplate restTemplate = new RestTemplate();


    private List<MauSac> listMauSac;
    private List<KichThuoc> listKichThuoc;
    private List<SanPham> listSanPham;
    private List<KieuTay> listKieuTay;
    private List<ChatLieu> listChatLieu;
        private List<KhuyenMai> listKhuyenMai;
    private List<ChiTietSanPham> listChiTietSanPham;






    @GetMapping("/hien-thi")
    public String view(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "status", required = false) String status,
                       @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "startDate", required = false) String startDateStr,
                       @RequestParam(value = "endDate", required = false) String endDateStr) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "ngayTao"));


        if (UserInfor.idNhanVien != null) {
            NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNhanVien).get();
            model.addAttribute("nv", nhanVien);
        }

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
//                    hd.setNgayCapNhat(hoaDon.getN);

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
                             @PathVariable("idHD") String idHD,
                             @RequestParam(value = "page", defaultValue = "0") String pageParam
    ) {

        NhanVien nhanVien = new NhanVien();
        if (UserInfor.idNhanVien != null) {
             nhanVien = nhanVienRepo.findById(UserInfor.idNhanVien).get();
            model.addAttribute("nv", nhanVien);
        }

        try {
            UUID uuid = UUID.fromString(idHD);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "ID hóa đơn không hợp lệ.");
            return "/view/view_tai/hoa_don/detail_bill.jsp";
        }


        // Lấy danh sách chi tiết hóa đơn
        List<ChiTietHoaDon> listHDCT = _hoaDonChiTietRepo.findAllByHoaDon_Id(idHD);
        // Lấy ra hóa đơn theo ID
//        HoaDon hoaDon = _hoaDonRepo.findById(idHD).orElse(null);
        HoaDon_Tai hoaDon = _hoaDonRepoTai.findById(idHD).orElse(null);
        if (hoaDon == null) {
            // Xử lý trường hợp không tìm thấy hóa đơn
            model.addAttribute("errorMessage", "Không tìm thấy hóa đơn.");
            return "/view/view_tai/hoa_don/detail_bill.jsp";
        }

        // Check if customer information is available
        KhachHang khachHang = hoaDon.getIdKhachHang();
//        KhachHang khachHang = _khachHangRepo.findByIdKH(hoaDon.getIdKhachHang().getId());
        if(nhanVien == null){
            nhanVien = _nhanVienRepo.findById("BF29DB87-6ED2-46E8-B34C-135B2EA4CCA6").get();
        }

        DiaChi diaChiKhachHang = new DiaChi();
        if(khachHang == null){
            khachHang = new KhachHang(); // Assume you have a default constructor
            khachHang.setHoTen("Khách lẻ"); // Default name
        }
        if (khachHang.getId() != null) {
            diaChiKhachHang = _diaChiRepository.findDiaChiByKhachHangId(khachHang.getId());
        }


        // Chuyển đổi từ HoaDon sang HoaDonDTO
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        HoaDonDTO hoaDonDTO = new HoaDonDTO(
                hoaDon.getId().toString(),
                hoaDon.getMa(),
                hoaDon.getIdKhachHang(),
                nhanVien,
                hoaDon.getPhuongThucThanhToan(),
                hoaDon.getTongTien(),
                hoaDon.getLoaiHoaDon(),
                hoaDon.getNgayTao() != null ? hoaDon.getNgayTao().format(dateTimeFormatter) : null,
                hoaDon.getNgayThanhToan() != null ? hoaDon.getNgayThanhToan().format(dateTimeFormatter) : null,
                hoaDon.getTrangThai(),
                hoaDon.getNgayCapNhat() != null ? hoaDon.getNgayCapNhat().format(dateTimeFormatter) : null,
                hoaDon.getGhiChu(),
                hoaDon.getNgayDaXacNhan() != null ? hoaDon.getNgayDaXacNhan().format(dateTimeFormatter) : null,
                hoaDon.getNgayChoGiaoHang() != null ? hoaDon.getNgayChoGiaoHang().format(dateTimeFormatter) : null,
                hoaDon.getNgayDangGiaoHang() != null ? hoaDon.getNgayDangGiaoHang().format(dateTimeFormatter) : null

        );
        HoaDonUtil hoaDonUtil = new HoaDonUtil();
        hoaDonDTO.setGhiChu(hoaDon.getGhiChu());
        hoaDonDTO.setTrangThaiText(hoaDonUtil.getTrangThaiName(hoaDon.getTrangThai()));
        hoaDonDTO.setMaMau(hoaDonUtil.getStatusClass(hoaDon.getTrangThai()));

        // Tính tổng tiền từ danh sách chi tiết hóa đơn
        BigDecimal tongTien = BigDecimal.ZERO;
        Map<String, HinhAnh> hinhAnhMap = new HashMap<>();
        for (ChiTietHoaDon hdct : listHDCT) {
            BigDecimal donGia = hdct.getDonGia();
            int soLuong = hdct.getSoLuong();
            tongTien = tongTien.add(donGia.multiply(BigDecimal.valueOf(soLuong)));

            // Lấy hình ảnh cho từng chi tiết sản phẩm
            HinhAnh hinhAnh = _hinhAnhRepo.findByIdCTSP(hdct.getIdCTSP().getId());
            if (hinhAnh != null) {
                hinhAnhMap.put(hdct.getIdCTSP().getId(), hinhAnh);
            }
        }

        Map<String, HinhAnh> hinhAnhMapCTSP = new HashMap<>();
        listChiTietSanPham = _chiTietSanPhamRepo.findAll();
        for (ChiTietSanPham ctsp : listChiTietSanPham) {
            HinhAnh hinhAnh = _hinhAnhRepo.findByIdCTSP(ctsp.getId());
            // Lấy hình ảnh cho từng chi tiết sản phẩm
            if (hinhAnh != null) {
                hinhAnhMapCTSP.put(ctsp.getId(), hinhAnh);
            }
        }



        // Lấy ra đối tượng giao hàng theo IdHoaDon
        GiaoHang giaoHang = _giaoHangRepo.findByHoaDonId(idHD);
        if (giaoHang == null) {
            model.addAttribute("errorDelivery", "Không tìm thấy đối tượng giao hàng");
        }

        GiaoHangDTO giaoHangDTO = GiaoHangDTO.toDTO(giaoHang);
        if (giaoHangDTO == null) {
            model.addAttribute("errorDelivery", "Không tìm thấy đối tượng giao hàng");
        }




        // Thêm các thông tin vào model để truyền sang JSP
        model.addAttribute("hinhAnhMap",hinhAnhMap);
        model.addAttribute("hinhAnhMapCTSP",hinhAnhMapCTSP);
        model.addAttribute("khachHang",khachHang);
        model.addAttribute("hoaDonDTO", hoaDonDTO);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("diaChiKhachHang", diaChiKhachHang);
        model.addAttribute("giaoHangDTO", giaoHangDTO);



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

        // Lấy ra danh sách chi tiết sản phẩm để hiển thị lên modal Thêm sản phẩm
        Pageable pageable = PageRequest.of(Integer.valueOf(pageParam), 10);
        Page<ChiTietSanPham> listCTSP = _sanPhamChiTietRepo.findByTrangThai(1, pageable);

        for (ChiTietSanPham ctsp : listCTSP) {
            if (ctsp.getSoLuong() <= 0) {
                listCTSP = _sanPhamChiTietRepo.findByTrangThai(_sanPhamChiTietRepo.INACTIVE, pageable);
            }
        }

        listMauSac = mauSacRepository.findAll();
        listKichThuoc = kichThuocRepo.findAll();
        listKieuTay = kieuTayRepo.findAll();
        listChatLieu = chatLieuRepo.findAll();
        listKhuyenMai = khuyenMaiRepo.findAll();
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listMauSac", listMauSac);
        model.addAttribute("listKichThuoc", listKichThuoc);
        model.addAttribute("listChatLieu", listChatLieu);
        model.addAttribute("listKieuTay", listKieuTay);
        model.addAttribute("listSanPham", listSanPham);
        model.addAttribute("listKM", listKhuyenMai);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("pageCTSP", listCTSP);

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

        Page<HoaDon> pageHD = hoaDonRepository.searchByKeyword(keyword, pageable);

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
            HoaDon_Tai hoaDon = _hoaDonRepoTai.findById(idHD).orElse(null);
//            ChiTietHoaDon chiTietHoaDon = _hoaDonChiTietRepo.findHDCTByIdHoaDon(idHD);

            if (hoaDon == null) {
                model.addAttribute("errorMessage", "Không tìm thấy hóa đơn.");
                redirectAttributes.addFlashAttribute("confirmError", "Không tìm thấy hóa đơn.");
                return "redirect:/hoa-don/detail/" + idHD;
            }

            NhanVien nhanVien = new NhanVien();
            if (UserInfor.idNhanVien != null){
                nhanVien  = _nhanVienRepo.findById(UserInfor.idNhanVien).get();

            }
//            GiaoHang giaoHang = _giaoHangRepo.findByHoaDonId(hoaDon.getId());
//            GiaoHangDTO giaoHangDTO = GiaoHangDTO.toDTO(giaoHang);


            // Cập nhật trạng thái và ngày giờ cụ thể tùy theo từng trường hợp
            switch (hoaDon.getTrangThai()) {
                case IHoaDonRepository.CHO_XAC_NHAN:
                    hoaDon.setTrangThai(IHoaDonRepository.DA_XAC_NHAN);
                    hoaDon.setIdNhanVien(nhanVien);
                    hoaDon.setNgayDaXacNhan(LocalDateTime.now());


                    // Lấy ra danh sách Chi tiết hóa đơn theo IDHoaDon
                    List<ChiTietHoaDon> chiTietList = _hoaDonChiTietRepo.findAllByHoaDon_Id(idHD);

                    for (ChiTietHoaDon chiTietHD : chiTietList) {
                        String idChiTietHoaDon = chiTietHD.getId();
                        // Lấy ra đối tượng chi tiết sản phẩm từ repository
                        Optional<ChiTietSanPham> chiTietSanPhamOptional = _chiTietSanPhamRepo.findByHoaDonChiTietId(idChiTietHoaDon);
                        if (chiTietSanPhamOptional.isPresent()) {
                            ChiTietSanPham chiTietSanPham = chiTietSanPhamOptional.get();

                            // Kiểm tra và cập nhật số lượng sản phẩm chi tiết
                            int soLuongConLai = chiTietSanPham.getSoLuong() - chiTietHD.getSoLuong();
                            if (soLuongConLai >= 0) {
                                chiTietSanPham.setSoLuong(soLuongConLai);
                                _chiTietSanPhamRepo.save(chiTietSanPham);


                            } else {
                                redirectAttributes.addFlashAttribute("errorProductDetail", "Không đủ số lượng sản phẩm trong kho");
                                return "redirect:/hoa-don/detail/" + idHD;
                            }
                        } else {
                            redirectAttributes.addFlashAttribute("errorProductDetail", "Không tìm thấy chi tiết sản phẩm có ID = " + idChiTietHoaDon);
                            return "redirect:/hoa-don/detail/" + idHD;
                        }
                    }


                    break;
                case IHoaDonRepository.DA_XAC_NHAN:
                    hoaDon.setTrangThai(IHoaDonRepository.CHO_GIAO_HANG);
                    hoaDon.setNgayChoGiaoHang(LocalDateTime.now());
                    hoaDon.setIdNhanVien(nhanVien);
//                    giaoHang.setNgayShip(Date.valueOf(LocalDate.now()));
//                    giaoHang.setPhiShip(giaoHangDTO.getPhiShip().intValue());

                    break;
                case IHoaDonRepository.CHO_GIAO_HANG:
                    hoaDon.setTrangThai(IHoaDonRepository.DANG_GIAO_HANG);
                    hoaDon.setNgayDangGiaoHang(LocalDateTime.now());
                    hoaDon.setIdNhanVien(nhanVien);


                    break;
                case IHoaDonRepository.DANG_GIAO_HANG:
                    hoaDon.setTrangThai(IHoaDonRepository.DA_HOAN_THANH);
                    hoaDon.setNgayCapNhat(LocalDateTime.now());
                    hoaDon.setIdNhanVien(nhanVien);
//                    giaoHang.setNgayNhan(Date.valueOf(LocalDate.now()));

                    break;
                default:
                    model.addAttribute("errorMessage", "Trạng thái không hợp lệ.");
                    return "redirect:/hoa-don/detail/" + idHD;
            }


            _hoaDonRepoTai.save(hoaDon);
//            _giaoHangRepo.save(giaoHang);
            HoaDonDTO hoaDonDTO = HoaDonDTO.fromEntity(hoaDon);
            redirectAttributes.addFlashAttribute("hoaDonDTO", hoaDonDTO);
//            redirectAttributes.addFlashAttribute("giaoHangDTO", giaoHangDTO);
            // Thông báo cập nhật thành công
            redirectAttributes.addFlashAttribute("confirmSuccess", "Cập nhật trạng thái đơn hàng thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("confirmError", "Có lỗi xảy ra khi cập nhật trạng thái đơn hàng.");
        }
        return "redirect:/hoa-don/detail/" + idHD;
    }


    @GetMapping("/hoan-tac/{idHD}")
    public String undoBill(@PathVariable("idHD") String idHD,

//                              @PathVariable("trangThai") int trangThai,
//                              @RequestParam("moTa") String moTa,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
//            HoaDon hoaDon = _hoaDonRepo.findById(idHD).orElse(null);
            HoaDon_Tai hoaDon = _hoaDonRepoTai.findById(idHD).orElse(null);

            if (hoaDon == null) {
                model.addAttribute("errorMessage", "Không tìm thấy hóa đơn.");
                redirectAttributes.addFlashAttribute("confirmError", "Không tìm thấy hóa đơn.");
                return "redirect:/hoa-don/detail/" + idHD;
            }

            GiaoHang giaoHang = _giaoHangRepo.findByHoaDonId(idHD);
            if (giaoHang == null) {
                model.addAttribute("errorDelivery", "Không tìm thấy đối tượng giao hàng");
            }

            GiaoHangDTO giaoHangDTO = GiaoHangDTO.toDTO(giaoHang);
            if (giaoHangDTO == null) {
                model.addAttribute("errorDelivery", "Không tìm thấy đối tượng giao hàng");
            }

            NhanVien nhanVien = new NhanVien();
            if (UserInfor.idNhanVien != null){
                nhanVien  = _nhanVienRepo.findById(UserInfor.idNhanVien).get();

            }


            switch (hoaDon.getTrangThai()) {
                case IHoaDonRepository.DA_XAC_NHAN:
                    hoaDon.setTrangThai(IHoaDonRepository.CHO_XAC_NHAN);
                    hoaDon.setNgayCapNhat(LocalDateTime.now());
                    hoaDon.setNgayDaXacNhan(null);
                    hoaDon.setIdNhanVien(nhanVien);
                    break;
                case IHoaDonRepository.CHO_GIAO_HANG:
                    hoaDon.setTrangThai(IHoaDonRepository.DA_XAC_NHAN);
                    hoaDon.setNgayCapNhat(LocalDateTime.now());
                    hoaDon.setNgayChoGiaoHang(null);
                    hoaDon.setIdNhanVien(nhanVien);
                    break;

                case IHoaDonRepository.DANG_GIAO_HANG:
                    hoaDon.setTrangThai(IHoaDonRepository.CHO_GIAO_HANG);
                    hoaDon.setNgayCapNhat(LocalDateTime.now());
                    hoaDon.setIdNhanVien(nhanVien);
                    break;
                case IHoaDonRepository.DA_HOAN_THANH:
                    hoaDon.setTrangThai(IHoaDonRepository.DANG_GIAO_HANG);
                    hoaDon.setNgayCapNhat(LocalDateTime.now());
                    hoaDon.setIdNhanVien(nhanVien);
                    break;
                default:
                    model.addAttribute("errorMessage", "Trạng thái không hợp lệ.");
                    return "redirect:/hoa-don/detail/" + idHD;
            }

            // Lưu lại thay đổi
            _hoaDonRepoTai.save(hoaDon);
            // Chuyển đổi HoaDon sang HoaDonDTO sau khi cập nhật và thêm vào model


            redirectAttributes.addFlashAttribute("confirmSuccess", "Cập nhật trạng thái đơn hàng thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("confirmError", "Có lỗi xảy ra khi cập nhật trạng thái đơn hàng.");
        }
        return "redirect:/hoa-don/detail/" + idHD;
    }



    @PostMapping("/cap-nhat/{hoaDonId}")
    public String updateDiaChi(@PathVariable("hoaDonId") String hoaDonId,
                               @ModelAttribute("giaoHangDTO") GiaoHangDTO giaoHangDTO,
                               @Param("tenTinhThanh") String tenTinhThanh,
                               @Param("tenQuanHuyen") String tenQuanHuyen,
                               @Param("tenPhuongXa") String tenPhuongXa,
                               RedirectAttributes redirectAttributes) {

        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId).get();
        HoaDon_Tai hoaDonTai = _hoaDonRepoTai.findById(hoaDonId).get();
        GiaoHang giaoHang = _giaoHangRepo.findByHoaDonId(hoaDonId);
        if (giaoHang == null) {
            // Nếu không tìm thấy, bạn có thể tạo mới nếu được phép
            giaoHang = new GiaoHang();
            giaoHang.setIdHoaDon(hoaDon); // Liên kết với HoaDon
        }
        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        hoaDonDTO.setTongTien(hoaDonTai.getTongTien());
         giaoHangDTO = GiaoHangDTO.toDTO(giaoHang);
        // Cập nhật các thông tin khác như phí ship, ghi chú nếu cần
        giaoHang.setTenNguoiNhan(giaoHangDTO.getTenNguoiNhan());
        giaoHang.setSdtNguoiNhan(giaoHangDTO.getSdtNguoiNhan());
        giaoHang.setDiaChiChiTiet(giaoHangDTO.getDiaChiChiTiet());
        giaoHang.setIdPhuongXa(tenPhuongXa);
        giaoHang.setIdQuanHuyen(tenQuanHuyen);
        giaoHang.setIdTinhThanh(tenTinhThanh);
        giaoHang.setPhiShip(giaoHangDTO.getPhiShip());
        giaoHang.setGhiChu(giaoHangDTO.getGhiChu());


        _giaoHangRepo.save(giaoHang);
        // Thêm thông báo thành công và chuyển hướng
        System.out.println("Thành công");
        redirectAttributes.addFlashAttribute("hoaDonDTO", hoaDonDTO);
        redirectAttributes.addFlashAttribute("giaoHangDTO", giaoHangDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin địa chỉ thành công.");
        return "redirect:/hoa-don/detail/" + hoaDonId;
    }


    // Chức năng hủy đơn hàng
    @PostMapping("/huy/{hoaDonId}")
    public String cancelOlder(@PathVariable("hoaDonId") String hoaDonId,
                               @RequestParam("lyDo") String lyDo,
                               RedirectAttributes redirectAttributes) {
        // Tìm đối tượng DiaChi theo IdHoaDon (hoaDonId)
        HoaDon_Tai hoaDonTai = _hoaDonRepoTai.findById(hoaDonId).get();

        if (hoaDonTai == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy thông tin đơn hàng");
            return "redirect:/hoa-don/detail/" + hoaDonId;
        }

        hoaDonTai.setTrangThai(hoaDonRepository.DA_HUY);
        hoaDonTai.setGhiChu(lyDo);
        hoaDonTai.setNgayCapNhat(LocalDateTime.now());
//        hoaDonTai.setIdNhanVien();


        // Lưu lại vào cơ sở dữ liệu
            _hoaDonRepoTai.save(hoaDonTai);
        // Thêm thông báo thành công và chuyển hướng
        System.out.println("Thành công");
        redirectAttributes.addFlashAttribute("cancelSuccess", "Hủy hóa đơn thành công");
        return "redirect:/hoa-don/hien-thi";
    }


    @PostMapping("/xac-nhan-thanh-toan")
    public String confirmPayment(
            @ModelAttribute HoaDonDTO hoaDonDTO,
            RedirectAttributes redirectAttributes) {
        try {
            HoaDon_Tai hoaDon = _hoaDonRepoTai.findById(hoaDonDTO.getId()).orElse(null);

            if (hoaDon == null) {
                redirectAttributes.addFlashAttribute("paymentError", "Không tìm thấy hóa đơn.");
                return "redirect:/hoa-don/detail/" + hoaDonDTO.getId();
            }

            hoaDon.setTongTien(hoaDonDTO.getTongTien());
            hoaDon.setTrangThai(IHoaDonRepository.DA_HOAN_THANH);
            hoaDon.setNgayThanhToan(LocalDateTime.now());
            hoaDon.setPhuongThucThanhToan(hoaDonDTO.getPhuongThucThanhToan());
            hoaDon.setGhiChu(hoaDonDTO.getGhiChu());

            _hoaDonRepoTai.save(hoaDon);

            // Add success message
            redirectAttributes.addFlashAttribute("confirmSuccess", "Cập nhật trạng thái đơn hàng thành công.");
            redirectAttributes.addFlashAttribute("hoaDonDTO", hoaDonDTO);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("confirmError", "Có lỗi xảy ra khi cập nhật trạng thái đơn hàng.");
        }
        return "redirect:/hoa-don/detail/" + hoaDonDTO.getId();
    }

    // Chức năng thếm sản phẩm vào giỏ hàng
    @GetMapping("/them-san-pham/{idCTSP}")
    public String addSanPhamVaoGioHang(@PathVariable("idCTSP") String idCTSP, @RequestParam(value = "page",defaultValue = "0") Optional<Integer> pageParam,
                                       @RequestParam("idHoaDon") String idHoaDon, RedirectAttributes redirectAttributes) {

        // Tìm hóa đơn chi tiết trong giỏ hàng của hóa đơn có id là idHoaDon
        List<ChiTietHoaDon> listHDCT = _hoaDonChiTietRepo.findAllByHoaDon_Id(idHoaDon);

        if (idHoaDon == null || idHoaDon.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy hóa đơn hoặc chi tiết sản phẩm");
            return "redirect:/hoa-don/detail/" + idHoaDon;
        }

        // Tìm sản phẩm chi tiết theo idCTSP
        Optional<ChiTietSanPham> optionalCTSP = _sanPhamChiTietRepo.findById(idCTSP);
        if (optionalCTSP.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sản phẩm chi tiết.");
            return "redirect:/hoa-don/detail/" + idHoaDon;
        }
        ChiTietSanPham chiTietSanPham = optionalCTSP.get();

        if(chiTietSanPham == null){
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy sản phẩm chi tiết.");
            return "redirect:/hoa-don/detail/" + idHoaDon;
        }

        // Kiểm tra xem sản phẩm chi tiết đã có trong giỏ hàng hay chưa
        boolean spTonTaiTrongGioHang = false;
        for (ChiTietHoaDon hdct : listHDCT) {
            if (hdct.getIdCTSP().getId().equals(idCTSP)) {
                // Nếu sản phẩm chi tiết đã có trong giỏ hàng, cộng dồn số lượng
                hdct.setSoLuong(hdct.getSoLuong() + 1);
                _hoaDonChiTietRepo.save(hdct);
                spTonTaiTrongGioHang = true;

                // Giảm số lượng của sản phẩm chi tiết trong kho (tạm thời không xử lý số lượng tồn)
//                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
//                _sanPhamChiTietRepo.save(chiTietSanPham);
                break;
            }
        }

        // Nếu sản phẩm chi tiết chưa có trong giỏ hàng, thêm mới vào giỏ hàng
        if (!spTonTaiTrongGioHang) {
            ChiTietHoaDon hdct = new ChiTietHoaDon();
            hdct.setIdCTSP(chiTietSanPham);
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(idHoaDon);
            hdct.setIdHoaDon(hoaDon);
            hdct.setSoLuong(1);

            // Số lượng mặc định khi thêm vào giỏ hàng là 1

            // Lấy giá của sản phẩm chi tiết để lưu vào chi tiết hóa đơn
            hdct.setDonGia(chiTietSanPham.getGiaBan());

            _hoaDonChiTietRepo.save(hdct);

            // Giảm số lượng của sản phẩm chi tiết trong kho (tạm thời không xử lý số lượng tồn)
//            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
//            _sanPhamChiTietRepo.save(chiTietSanPham);
        }


        // Tính tổng tiền
        BigDecimal tongTien = BigDecimal.ZERO;
        for (ChiTietHoaDon hdct : listHDCT) {
            BigDecimal donGia = hdct.getDonGia();
            int soLuong = hdct.getSoLuong();
            tongTien = tongTien.add(donGia.multiply(BigDecimal.valueOf(soLuong)));
        }

        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        hoaDonDTO.setTongTien(tongTien);

        redirectAttributes.addFlashAttribute("hoaDonDTO", hoaDonDTO);
        redirectAttributes.addFlashAttribute("addProductSuccess","Thêm sản phẩm vào giỏ hàng thành công");
        return "redirect:/hoa-don/detail/" + idHoaDon;
    }




    // Chức năng xóa sản phẩm chi tiết khỏi hoa đơn chi tiết
    @GetMapping("/xoa-san-pham/{idCTSP}")
    public ResponseEntity<String> xoaSanPhamChiTiet(@PathVariable("idCTSP") String idCTSP,
                                                    @RequestParam("idHoaDon") String idHoaDon) {
        try {
            // Kiểm tra và xóa sản phẩm chi tiết
            Optional<ChiTietSanPham> optionalCTSP = _sanPhamChiTietRepo.findById(idCTSP);
            if (optionalCTSP.isEmpty()) {
                return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm chi tiết.");
            }
            ChiTietSanPham chiTietSanPham = optionalCTSP.get();

            // Xóa sản phẩm chi tiết từ hóa đơn
            int deletedCount = _hoaDonChiTietReposioty.deleteByHoaDon_IdAndIdCTSP_Id(idHoaDon, idCTSP);
            if (deletedCount == 0) {
                return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm trong giỏ hàng.");
            }

            return ResponseEntity.ok("Xóa sản phẩm thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi xóa sản phẩm.");
        }
    }

    @GetMapping("/cap-nhat-so-luong-san-pham/{idCTSP}")
    public ResponseEntity<String> updateSoLuong(
            @PathVariable String idCTSP,
            @RequestParam int soLuong,
            @RequestParam("idHoaDon") String idHoaDon
    ) {
        try {
            ChiTietSanPham chiTietSanPham = _sanPhamChiTietRepo.findByIdCTSP(idCTSP);

            // Kiểm tra số lượng nhập vào hợp lệ
            if (soLuong <= 0) {
                return ResponseEntity.badRequest().body("Số lượng phải lớn hơn 0.");
            }

            // Kiểm tra số lượng nhập vào không được vượt quá số lượng trong kho
            if (soLuong > chiTietSanPham.getSoLuong()) {
                return ResponseEntity.badRequest().body("Số lượng không được vượt quá số lượng trong kho.");
            }

            // Lấy danh sách chi tiết hóa đơn và cập nhật số lượng
            List<ChiTietHoaDon> listHDCT = _hoaDonChiTietRepo.findAllByHoaDon_Id(idHoaDon);

            if (idHoaDon == null || idHoaDon.isEmpty()) {
                return ResponseEntity.badRequest().body("Mã hóa đơn không hợp lệ.");
            }

            for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
                if (chiTietHoaDon.getIdCTSP().getId().equals(idCTSP)) {
                    chiTietHoaDon.setSoLuong(soLuong);
                    _hoaDonChiTietRepo.save(chiTietHoaDon);
                    break;
                }
            }

            return ResponseEntity.ok("Cập nhật số lượng thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi cập nhật số lượng.");
        }
    }


}

