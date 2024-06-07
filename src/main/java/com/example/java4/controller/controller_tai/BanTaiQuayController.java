package com.example.java4.controller.controller_tai;

import com.example.java4.entities.*;
import com.example.java4.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ban-hang-tai-quay")
public class BanTaiQuayController {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HDCTRepository hoaDonChiTietRepository;


    @Autowired
    private SPCTRepository sanPhamChiTietRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    SanPhamRepository sanPhamRepo;

    @Autowired
    KichThuocRepository kichThuocRepo;

    @Autowired
    ChatLieuRepository chatLieuRepo;

    @Autowired
    KieuTayRepository kieuTayRepo;

    @Autowired
    NhanVienRepository nhanVienRepo;

    private List<HoaDon> listHoaDon;
    private List<ChiTietHoaDon> listHDCT;
    private List<ChiTietSanPham> listCTSP;
    private List<KhachHang> listKH;
    private List<MauSac> listMauSac;
    private List<KichThuoc> listKichThuoc;
    private List<SanPham> listSanPham;
    private List<KieuTay> listKieuTay;
    private List<ChatLieu> listChatLieu;
    private String idNV = "FE755A99-83D6-4431-8FDE-4DF25C6B8BD0";

@GetMapping("")
public String hienThi(Model model,@RequestParam(value = "page",defaultValue ="0") String pageParam ) {
    System.out.println("========================================= test paa"+pageParam);
    Pageable pageable = PageRequest.of(Integer.valueOf(pageParam), 10);
    listHoaDon = hoaDonRepository.selectTop5();
    Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findByTrangThai(1,pageable);
    listKH = khachHangRepository.findAll();
    listMauSac = mauSacRepository.findAll();
    listKichThuoc = kichThuocRepo.findAll();
    listKieuTay = kieuTayRepo.findAll();
    listSanPham = sanPhamRepo.findAll();
    listChatLieu = chatLieuRepo.findAll();
    model.addAttribute("listMauSac", listMauSac);
    model.addAttribute("listKichThuoc", listKichThuoc);
    model.addAttribute("listChatLieu", listChatLieu);
    model.addAttribute("listKieuTay", listKieuTay);
    model.addAttribute("listSanPham", listSanPham);
    model.addAttribute("listHoaDon", listHoaDon);
    model.addAttribute("listCTSP", listCTSP);
    model.addAttribute("listKH", listKH);
    System.out.println(listMauSac);
    return "/view/view_payment_counter/banHangTaiQuay.jsp";
}
    @GetMapping("detail-hoa-don/{idHD}")
    public String detailHoaDon(@PathVariable String idHD,@RequestParam Optional<Integer> pageParam, Model model) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        model.addAttribute("hoaDon", hoaDon.get());
        listHoaDon = hoaDonRepository.selectTop5();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();
        listMauSac = mauSacRepository.findAll();
        listKichThuoc = kichThuocRepo.findAll();
        listKieuTay = kieuTayRepo.findAll();
        listSanPham = sanPhamRepo.findAll();
        listChatLieu = chatLieuRepo.findAll();
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findAll(pageable);
        model.addAttribute("listHoaDon", listHoaDon);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("listKH", listKH);
        model.addAttribute("listMauSac", listMauSac);
        model.addAttribute("listKichThuoc", listKichThuoc);
        model.addAttribute("listChatLieu", listChatLieu);
        model.addAttribute("listKieuTay", listKieuTay);
        model.addAttribute("listSanPham", listSanPham);
        //Lọc hóa đơn chi tiết theo id hóa đơn
        List<ChiTietHoaDon> gioHangTheoHoaDon = new ArrayList<>();
        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
            if (chiTietHoaDon.getIdHoaDon().getId().equals(idHD)) {
                gioHangTheoHoaDon.add(chiTietHoaDon);
                //Tính tổng tiền từng hóa đơn
                BigDecimal tongTien = BigDecimal.ZERO;
                for (ChiTietHoaDon hd : gioHangTheoHoaDon) {
                    int sL = hd.getSoLuong();
                    BigDecimal donGia = BigDecimal.valueOf(hd.getDonGia());
                    BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(sL));
                    tongTien = tongTien.add(thanhTien);
                }
                model.addAttribute("tongTien", tongTien);
            }
        }
        model.addAttribute("listHDCT", gioHangTheoHoaDon);
        return "/view/view_payment_counter/banHangTaiQuay.jsp";
    }


    @PostMapping("/delete-hdct/{idHDCT}")
    public String deleteHDCT(@PathVariable String idHDCT){
    HoaDon hd = new HoaDon();
    int count = 0;
    ChiTietSanPham ctsp = new ChiTietSanPham();
        for (ChiTietHoaDon hdct:listHDCT){
            if (hdct.getId().equals(idHDCT)){
                hoaDonChiTietRepository.delete(hdct);
                hd = hdct.getIdHoaDon();
                ctsp = hdct.getIdCTSP();
                count=hdct.getSoLuong();
                System.out.println("test data :========================== ");
            }
        }
        ctsp.setSoLuong(ctsp.getSoLuong()+count);
        sanPhamChiTietRepository.save(ctsp);
        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + hd.getId();
    }

    @PostMapping("/add-hoa-don")
    public String themHoaDon(RedirectAttributes redirectAttributes) {
        List<HoaDon> hoaDonList = this.hoaDonRepository.selectTop5();
        int currentHoaDonCount = hoaDonList.size();
        // Nếu số lượng hóa đơn lớn hơn 5, gửi dữ liệu Error từ Controller sang View(file.jsp)
        if (currentHoaDonCount >= 5) {
            redirectAttributes.addFlashAttribute("currentHoaDonCount", currentHoaDonCount);
            redirectAttributes.addFlashAttribute("errorBillMax", "Bạn chỉ có thể tạo tối đa 5 đơn hàng");
            return "redirect:/ban-hang-tai-quay";
        }
        String ma1="HD";
        Integer sum = hoaDonRepository.countHD() + 1;
        String ma = ma1 + sum;
        System.out.println("==============test hoa don:"+ma);
        LocalDateTime now =LocalDateTime.now();
        HoaDon hoaDon = new HoaDon();
        //Tạo mã tự sinh
        hoaDon.setNgayTao(now);
        Optional<NhanVien> nv = nhanVienRepo.findById(idNV);
        hoaDon.setIdNhanVien(nv.get());
        hoaDon.setMa(ma);
        hoaDon.setTrangThai(0);
        try {
            //gửi dữ liệu success từ Controller sang View(file.jsp)
            this.hoaDonRepository.save(hoaDon);
            redirectAttributes.addFlashAttribute("success", "Hóa đơn được tạo thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Đã xảy ra lỗi khi tạo hóa đơn.");
        }
        hoaDonRepository.save(hoaDon);
        return "redirect:/ban-hang-tai-quay";
    }

    @PostMapping("giam-so-luong/{idCTSP}")
    public String giamSoLuong(@PathVariable String idCTSP, @RequestParam String idHoaDon) {
        //Giảm số lượng sản phẩm -1 khi ấn vào button giảm trong giỏ hàng
        listCTSP = sanPhamChiTietRepository.findAll();
        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
            if (chiTietHoaDon.getIdCTSP().getId().equals(idCTSP) && chiTietHoaDon.getIdHoaDon().getId().equals(idHoaDon)) {
                chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong() - 1);
                hoaDonChiTietRepository.save(chiTietHoaDon);
                //Khi số lượng sản phẩm trong giở hàng <= 0 thì set số lượng = 1
                if (chiTietHoaDon.getSoLuong() <= 0) {
                    chiTietHoaDon.setSoLuong(1);
                    hoaDonChiTietRepository.save(chiTietHoaDon);
                } else {
                    //Số lượng của sản phẩm chi tiết được +1 khi ấn vào button thêm trong giỏ hàng
                    for (ChiTietSanPham chiTietSanPham : listCTSP) {
                        if (chiTietSanPham.getId().equals(idCTSP)) {
                            chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + 1);
                            sanPhamChiTietRepository.save(chiTietSanPham);
                        }
                    }
                }
            }
        }
        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
    }


    @PostMapping("them-so-luong/{idCTSP}")
    public String themSoLuong(@PathVariable String idCTSP, @RequestParam String idHoaDon, RedirectAttributes redirectAttributes) {
        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(idCTSP);
        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
            //Nếu số lượng trong spct = 0 thì không đưuọc thêm sản phẩm nữa
            if (chiTietSanPham.getSoLuong() <= 0) {
                chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());
                try {
                    hoaDonChiTietRepository.save(chiTietHoaDon);
                    redirectAttributes.addFlashAttribute("error", "Hết hàng");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //Thêm số lượng sản phẩm +1 khi ấn vào button thêm trong giỏ hàng
                if (chiTietHoaDon.getIdCTSP().getId().equals(idCTSP) && chiTietHoaDon.getIdHoaDon().getId().equals(idHoaDon)) {
                    chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong() + 1);
                    hoaDonChiTietRepository.save(chiTietHoaDon);

                    //Số lượng của sản phẩm chi tiết -1 khi ấn vào button thêm trong giỏ hàng
                    if (chiTietSanPham.getId().equals(idCTSP)) {
                        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
                        sanPhamChiTietRepository.save(chiTietSanPham);
                    }
                }

            }
        }
        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
    }



    @PostMapping("add-san-pham/{idCTSP}")
    public String addSanPhamVaoGioHang(@PathVariable String idCTSP, @RequestParam("page") Optional<Integer> pageParam,
                                       @RequestParam String idHoaDon, RedirectAttributes redirectAttributes) {
        ChiTietHoaDon hdct = new ChiTietHoaDon();
        //Tìm sản phẩm trong giỏ hàng
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findAll(pageable);
        boolean spTonTaiTrongGioHang = false;
        Integer slBanDau = 1;
        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(idCTSP);
        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
            //Nếu số lượng trong spct = 0 thì không đưuọc thêm sản phẩm nữa
            if (chiTietSanPham.getSoLuong() <= 0) {
                chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());
                try {
                    hoaDonChiTietRepository.save(chiTietHoaDon);
                    spTonTaiTrongGioHang = true;
                    hdct.setSoLuong(hdct.getSoLuong()+1);
                    hoaDonChiTietRepository.save(hdct);
                    redirectAttributes.addFlashAttribute("error", "Hết hàng");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            } else {
                if (chiTietHoaDon.getIdCTSP().getId().equals(idCTSP) && chiTietHoaDon.getIdHoaDon().getId().equals(idHoaDon)) {
                    //Thêm số lượng sản phẩm +1 khi ấn vào button thêm trong giỏ hàng
                    chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong() + 1);
                    hoaDonChiTietRepository.save(chiTietHoaDon);

                    //Số lượng của sản phẩm chi tiết bị -1 khi ấn vào button thêm trong giỏ hàng
                    if (chiTietSanPham.getId().equals(idCTSP)) {
                        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
                        sanPhamChiTietRepository.save(chiTietSanPham);
                    }
                    spTonTaiTrongGioHang = true;
                    break;
                }
            }
        }

        Integer donGia = null;
        if (!spTonTaiTrongGioHang) {
            for (ChiTietSanPham sp : listCTSP) {
                if (sp.getId().equals(idCTSP)) {
                    donGia = sp.getGiaBan();
                }
            }
            ChiTietSanPham ctsp = new ChiTietSanPham();
            ctsp.setId(idCTSP);
            hdct.setIdCTSP(ctsp);
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(idHoaDon);
            hdct.setIdHoaDon(hoaDon);
            hdct.setSoLuong(1);
            hdct.setDonGia(donGia);
            hoaDonChiTietRepository.save(hdct);
            //Số lượng của sản phẩm chi tiết bị giảm 1 khi ấn vào button thêm trong giỏ hàng
            if (chiTietSanPham.getId().equals(idCTSP)) {
                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
                sanPhamChiTietRepository.save(chiTietSanPham);
            }
        }
        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
    }
//
//    @PostMapping("add-san-pham/{idCTSP}")
//    public String addSanPhamVaoGioHang(@PathVariable String idCTSP,
//                                       @RequestParam String idHoaDon){
//        ChiTietHoaDon hdct = new ChiTietHoaDon();
//
//        //Tìm sản phẩm trong giỏ hàng
//        boolean spTonTaiTrongGioHang = false;
//        Integer slBanDau = 1;
//        for (ChiTietHoaDon sp:listHDCT){
//            if (sp.getIdCTSP().getId().equals(idCTSP) && sp.getIdHoaDon().getId().equals(idHoaDon)){
//                System.out.println("idCTSP"+sp.getIdCTSP().getId());
//                System.out.println("ID hóa đơn"+sp.getIdHoaDon().getId());
//
//                sp.setSoLuong(sp.getSoLuong()+1);
//                hoaDonChiTietRepository.save(sp);
//
////                slBanDau = sp.getSoLuongMua();
//                spTonTaiTrongGioHang=true;
//                System.out.println("-------Số lượng sản phẩm----------"+sp.getSoLuong());
//                System.out.println("-------Số lượng sản test----------"+slBanDau);
//                break;
//            }
//        }
//
//        Integer donGia = null;
//        if (!spTonTaiTrongGioHang){
//            for (ChiTietSanPham sp:listCTSP){
//                if (sp.getId().equals(idCTSP)){
//                    donGia = sp.getGiaBan();
//                }
//            }
//
//            ChiTietSanPham ctsp = new ChiTietSanPham();
//            ctsp.setId(idCTSP);
//            hdct.setIdCTSP(ctsp);
//
//            HoaDon hoaDon = new HoaDon();
//            hoaDon.setId(idHoaDon);
//            hdct.setIdHoaDon(hoaDon);
//            hdct.setSoLuong(1);
//            hdct.setDonGia(donGia);
//
//            hoaDonChiTietRepository.save(hdct);
//
//        }
//
//        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
//    }
    //Thanh toán
    @PostMapping("/thanh-toan/{idHoaDon}")
    public String thanhToanSanPham(@PathVariable String idHoaDon,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao) {
        for (int i = 0; i < listHoaDon.size(); i++) {
            if (listHoaDon.get(i).getId().equals(idHoaDon)) {
                HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
                hoaDon.setId(idHoaDon);
                hoaDon.setMa(hoaDon.getMa());
                hoaDon.setTongTien(hoaDonChiTietRepository.tinhGiaTriHD(idHoaDon));
                hoaDon.setTrangThai(1);
                hoaDon.setPhuongThucThanhToan(1);
                hoaDon.setIdKhachHang(khachHangRepository.findById("DB45F5AA-6559-43B0-8F4A-B01E6FF471A1").get());
                hoaDon.setNgayThanhToan(ngayTao);
                hoaDonRepository.save(hoaDon);
            }
        }
        return "redirect:/ban-hang-tai-quay";
    }

    //Cập nhật só lượng sau khi thanh toán
    public void capMhatSoLuong() {
        listCTSP = sanPhamChiTietRepository.findAll();
        for (ChiTietHoaDon hdct : listHDCT) {
            String idSPCT = hdct.getIdCTSP().getId();
            int soLuong = hdct.getSoLuong();
            for (ChiTietSanPham sp : listCTSP) {
                if (sp.getId().equals(idSPCT)) {
                    sp.setSoLuong(sp.getSoLuong() - soLuong);
                    sanPhamChiTietRepository.save(sp);
                }
            }
        }
    }

    //=====GHÉP=====
    //Search sản phẩm chi tiết
    @PostMapping("search")
    public String search(
            Model model,
            @RequestParam("search") String search,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.timKiem(search, SPCTRepository.ACTIVE, pageable);
        listHoaDon = hoaDonRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();
        model.addAttribute("listHoaDon",listHoaDon);
        model.addAttribute("listCTSP",listCTSP);
        model.addAttribute("listKH",listKH);
        return "/view/view_payment_counter/banHangTaiQuay.jsp";
    }

    //Lọc sản phẩm
    @GetMapping("locSPCTBySanPham/{idSanPham}")
    public String locSPCTBySanPham(
            Model model,
            @PathVariable("idSanPham") String idSanPham,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        listHoaDon = hoaDonRepository.selectTop5();
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdSanPham(idSanPham,SPCTRepository.ACTIVE, pageable);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepository.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        model.addAttribute("listHoaDon",  listHoaDon);
        return "/view/view_payment_counter/banHangTaiQuay.jsp";
    }

    //Lọc màu sắc
    @GetMapping("locSPCTByMauSac/{idMauSac}")
    public String locSPCTByMauSac(
            Model model,
            @PathVariable("idMauSac") String idMauSac,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        listHoaDon = hoaDonRepository.selectTop5();
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdMauSac(idMauSac,SPCTRepository.ACTIVE, pageable);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepository.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        model.addAttribute("listHoaDon",listHoaDon);
        return "/view/view_payment_counter/banHangTaiQuay.jsp";
    }
    //Lọc kích thước
    @GetMapping("locSPCTByKichThuoc/{idKichThuoc}")
    public String locSPCTByKichThuoc(
            Model model,
            @PathVariable("idKichThuoc") String idKichThuoc,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        listHoaDon = hoaDonRepository.selectTop5();
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdKichThuoc(idKichThuoc,SPCTRepository.ACTIVE, pageable);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepository.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        model.addAttribute("listHoaDon", listHoaDon);
        return "/view/view_payment_counter/banHangTaiQuay.jsp";
    }
    //Lọc chất liệu
    @GetMapping("locSPCTByChatLieu/{idChatLieu}")
    public String locSPCTByChatLieu(
            Model model,
            @PathVariable("idChatLieu") String idChatLieu,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        listHoaDon = hoaDonRepository.selectTop5();
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdChatLieu(idChatLieu,SPCTRepository.ACTIVE, pageable);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepository.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        model.addAttribute("listHoaDon",listHoaDon);
        return "/view/view_payment_counter/banHangTaiQuay.jsp";
    }
    //Lọc kiểu tay
    @GetMapping("locSPCTByKieuTay/{idKieuTay}")
    public String locSPCTByKieuTay(
            Model model,
            @PathVariable("idKieuTay") String idKieuTay,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        listHoaDon = hoaDonRepository.selectTop5();
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdKieuTay(idKieuTay,SPCTRepository.ACTIVE, pageable);
        Optional<NhanVien> nv = nhanVienRepo.findById(idNV);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepository.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        model.addAttribute("nhanVien", nv.get());
        model.addAttribute("listHoaDon",listHoaDon);
        return "/view/view_payment_counter/banHangTaiQuay.jsp";
    }
        //lọc sản phẩm chi tiết
    @PostMapping("filter")
    public String filter(
            Model model,
            @RequestParam("idSanPham") String idSanPham,
            @RequestParam("idMauSac") String idMauSac,
            @RequestParam("idKichThuoc") String idKichThuoc,
            @RequestParam("idChatLieu") String idChatLieu,
            @RequestParam("idKieuTay") String idKieuTay,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        System.out.println("=========================================SANPHAM:===="+idSanPham);
        System.out.println("=========================================MAUSAC:===="+idMauSac);
        System.out.println("=========================================KICHTHUOC:===="+idKichThuoc);
        System.out.println("=========================================CHATLIEU:===="+idChatLieu);
        System.out.println("=========================================KIEUTAY:===="+idKieuTay);
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
//        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findOneCombobox(idSanPham, idMauSac, idKichThuoc, idChatLieu, idKieuTay, pageable);

        listHoaDon = hoaDonRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();
        model.addAttribute("listHoaDon",listHoaDon);
        model.addAttribute("listCTSP",listCTSP);
        model.addAttribute("listKH",listKH);
        return "/view/view_payment_counter/banHangTaiQuay.jsp";
    }

}
