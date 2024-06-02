package com.example.java4.controller.controller_viet;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.entities.HoaDon;
import com.example.java4.entities.KhachHang;
import com.example.java4.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("store")
public class Controller_BanTaiQuay {
    int count = 0;
    @Autowired
    SanPhamRepository sanPhamRepo;
    @Autowired
    MauSacRepository mauSacRepo;
    @Autowired
    KichThuocRepository kichThuocRepo;
    @Autowired
    ChatLieuRepository chatLieuRepo;
    @Autowired
    KieuTayRepository kieuTayRepo;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HDCTRepository hoaDonChiTietRepository;
    @Autowired
    private SPCTRepository sanPhamChiTietRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;

    private List<HoaDon> listHoaDon;
    private List<ChiTietHoaDon> listHDCT;
    private List<ChiTietSanPham> listCTSP;
    private List<KhachHang> listKH;

    @GetMapping("ban-tai-quay")
    public String getBanTaiQuay(Model model, @RequestParam("page") Optional<Integer> pageParam){
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findByTrangThai(SPCTRepository.ACTIVE, pageable);
        listHoaDon = hoaDonRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();
        model.addAttribute("listHoaDon",listHoaDon);
        model.addAttribute("listCTSP",listCTSP);
//        model.addAttribute("listHDCT",listHDCT);
        model.addAttribute("listKH",listKH);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepo.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        return "/view/view_viet/banHangTaiQuay.jsp";
    }

    @PostMapping("add-hoa-don")
    public String themHoaDon(){
        HoaDon hoaDon = new HoaDon();

//        KhachHang khachHang = new KhachHang();
//        khachHang.setId(1);
//        hoaDon.setIdKhachHang(khachHang);


        hoaDon.setTrangThai(0);
        hoaDon.setNgayTao(new Date());

        hoaDonRepository.save(hoaDon);

        return "redirect:/store/ban-tai-quay";
    }

    @GetMapping("detail-hoa-don/{idHD}")
    public String detailHoaDon(@PathVariable String idHD, Model model){
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        model.addAttribute("hoaDon",hoaDon.get());

        listHoaDon = hoaDonRepository.findAll();
        listCTSP = sanPhamChiTietRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();

        model.addAttribute("listHoaDon",listHoaDon);
        model.addAttribute("listCTSP",listCTSP);

        model.addAttribute("listKH",listKH);

        //Lọc hóa đơn chi tiết theo id hóa đơn
        List<ChiTietHoaDon> gioHangTheoHoaDon = new ArrayList<>();
        for (ChiTietHoaDon ct:listHDCT){
            if (ct.getIdHoaDon().getId().equals(idHD)){
                gioHangTheoHoaDon.add(ct);

                //Tính tổng tiền từng hóa đơn
                BigDecimal tongTien = BigDecimal.ZERO;
                for (ChiTietHoaDon hd:gioHangTheoHoaDon){
                    int sL = hd.getSoLuong();
                    BigDecimal donGia = BigDecimal.valueOf(hd.getDonGia());
                    BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(sL));
                    tongTien = tongTien.add(thanhTien);
                }
                model.addAttribute("tongTien",tongTien);
                model.addAttribute("listHDCT",gioHangTheoHoaDon);
            }
        }


        return "/view/view_viet/banHangTaiQuay.jsp";
    }

    @PostMapping("add-san-pham/{idCTSP}")
    public String addSanPhamVaoGioHang(@PathVariable String idCTSP,
                                       @RequestParam String idHoaDon){
        ChiTietHoaDon hdct = new ChiTietHoaDon();

        //Tìm sản phẩm trong giỏ hàng
        boolean spTonTaiTrongGioHang = false;
        Integer slBanDau = 1;
        for (ChiTietHoaDon sp:listHDCT){
            if (sp.getIdCTSP().getId().equals(idCTSP) && sp.getIdHoaDon().getId().equals(idHoaDon)){
                System.out.println("idCTSP"+sp.getIdCTSP().getId());
                System.out.println("ID hóa đơn"+sp.getIdHoaDon().getId());

                sp.setSoLuong(sp.getSoLuong()+1);
                hoaDonChiTietRepository.save(sp);

//                slBanDau = sp.getSoLuongMua();
                spTonTaiTrongGioHang=true;
                System.out.println("-------Số lượng sản phẩm----------"+sp.getSoLuong());
                System.out.println("-------Số lượng sản test----------"+slBanDau);
                break;
            }
        }

        Integer donGia = null;
        if (!spTonTaiTrongGioHang){
            for (ChiTietSanPham sp:listCTSP){
                if (sp.getId().equals(idCTSP)){
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

        }

        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
    }

    @PostMapping("/delete-hdct/{idHDCT}")
    public String deleteHDCT(@PathVariable Integer idHDCT,
                             @RequestParam Integer idHoaDon){

        for (ChiTietHoaDon hdct:listHDCT){
            if (hdct.getId().equals(idHDCT)){
                hoaDonChiTietRepository.delete(hdct);
            }
        }

        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
    }

    //Thanh toán
    @PostMapping("/thanh-toan/{idHoaDon}")
    public String thanhToanSanPham(@PathVariable String idHoaDon,
                                   @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date ngayTao,
                                   @RequestParam String idKhachHang){

        for (int i=0;i<listHoaDon.size();i++){
            if (listHoaDon.get(i).getId().equals(idHoaDon)){
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(idHoaDon);
                KhachHang khachHang = new KhachHang();
                khachHang.setId(idKhachHang);
                hoaDon.setIdKhachHang(khachHang);
                capMhatSoLuong();
                hoaDon.setTrangThai(1);
                hoaDon.setNgayTao(ngayTao);
                hoaDonRepository.save(hoaDon);
            }
        }

        return "redirect:/ban-hang";
    }

    //Cập nhật só lượng sau khi thanh toán
    public void capMhatSoLuong(){
        for (ChiTietHoaDon hdct:listHDCT){
            String idSPCT = hdct.getIdCTSP().getId();
            int soLuong = hdct.getSoLuong();
            for (ChiTietSanPham sp:listCTSP){
                if (sp.getId().equals(idSPCT)){
                    sp.setSoLuong(sp.getSoLuong()-soLuong);
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
        return "/view/view_viet/banHangTaiQuay.jsp";
    }

    //Lọc sản phẩm
    @GetMapping("locSPCTBySanPham/{idSanPham}")
    public String locSPCTBySanPham(
            Model model,
            @PathVariable("idSanPham") String idSanPham,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdSanPham(idSanPham,SPCTRepository.ACTIVE, pageable);

        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepo.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        return "/view/view_viet/banHangTaiQuay.jsp";
    }

    //Lọc màu sắc
    @GetMapping("locSPCTByMauSac/{idMauSac}")
    public String locSPCTByMauSac(
            Model model,
            @PathVariable("idMauSac") String idMauSac,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdMauSac(idMauSac,SPCTRepository.ACTIVE, pageable);

        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepo.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        return "/view/view_viet/banHangTaiQuay.jsp";
    }

    //Lọc kích thước
    @GetMapping("locSPCTByKichThuoc/{idKichThuoc}")
    public String locSPCTByKichThuoc(
            Model model,
            @PathVariable("idKichThuoc") String idKichThuoc,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdKichThuoc(idKichThuoc,SPCTRepository.ACTIVE, pageable);

        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepo.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        return "/view/view_viet/banHangTaiQuay.jsp";
    }

    //Lọc chất liệu
    @GetMapping("locSPCTByChatLieu/{idChatLieu}")
    public String locSPCTByChatLieu(
            Model model,
            @PathVariable("idChatLieu") String idChatLieu,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdChatLieu(idChatLieu,SPCTRepository.ACTIVE, pageable);

        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepo.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        return "/view/view_viet/banHangTaiQuay.jsp";
    }

    //Lọc kiểu tay
    @GetMapping("locSPCTByKieuTay/{idKieuTay}")
    public String locSPCTByKieuTay(
            Model model,
            @PathVariable("idKieuTay") String idKieuTay,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdKieuTay(idKieuTay,SPCTRepository.ACTIVE, pageable);

        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepo.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
        return "/view/view_viet/banHangTaiQuay.jsp";
    }

//    //lọc sản phẩm chi tiết
//    @PostMapping("filter")
//    public String filter(
//            Model model,
//            @RequestParam("idSanPham") String idSanPham,
//            @RequestParam("idMauSac") String idMauSac,
//            @RequestParam("idKichThuoc") String idKichThuoc,
//            @RequestParam("idChatLieu") String idChatLieu,
//            @RequestParam("idKieuTay") String idKieuTay,
//            @RequestParam("page") Optional<Integer> pageParam
//    ){
//        System.out.println("=========================================SANPHAM:===="+idSanPham);
//        System.out.println("=========================================MAUSAC:===="+idMauSac);
//        System.out.println("=========================================KICHTHUOC:===="+idKichThuoc);
//        System.out.println("=========================================CHATLIEU:===="+idChatLieu);
//        System.out.println("=========================================KIEUTAY:===="+idKieuTay);
//
//        Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
////        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findOneCombobox(idSanPham, idMauSac, idKichThuoc, idChatLieu, idKieuTay, pageable);
//
//
//        listHoaDon = hoaDonRepository.findAll();
//        listHDCT = hoaDonChiTietRepository.findAll();
//        listKH = khachHangRepository.findAll();
//        model.addAttribute("listHoaDon",listHoaDon);
//        model.addAttribute("listCTSP",listCTSP);
//        model.addAttribute("listKH",listKH);
//        return "/view/view_viet/banHangTaiQuay.jsp";
//    }


}
