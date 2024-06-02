package com.example.java4.controller.controller_sang;

import com.example.java4.entities.*;
import com.example.java4.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ban-hang-disable")
public class Test_BanTaiQuayController {

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private KichThuocRepository kichThuocRepository;

//    @Autowired
//    private Chat mauSacRepository;

//    @Autowired
//    private MauSacRepository mauSacRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HDCTRepository hoaDonChiTietRepository;

    @Autowired
    private SPCTRepository_Sang sanPhamChiTietRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    private List<HoaDon> listHoaDon;
    private List<ChiTietHoaDon> listHDCT;
    private List<ChiTietSanPham> listCTSP;
    private List<KhachHang> listKH;
    private List<MauSac> listMauSac;

    @GetMapping("")
    public String hienThi(Model model) {
        listHoaDon = hoaDonRepository.findAll();
        listCTSP = sanPhamChiTietRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();
        listMauSac = mauSacRepository.findAll();
        model.addAttribute("listHoaDon", listHoaDon);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("listKH", listKH);
        model.addAttribute("listMauSac", listMauSac);
        System.out.println(listMauSac);
        return "/view/view_sang/banHangTaiQuay.jsp";
    }

    @PostMapping("add-hoa-don")
    public String themHoaDon() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTrangThai(0);
        hoaDon.setNgayTao(new Date());

        hoaDonRepository.save(hoaDon);

        return "redirect:/ban-hang";
    }

    @GetMapping("detail-hoa-don/{idHD}")
    public String detailHoaDon(@PathVariable String idHD, Model model) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        model.addAttribute("hoaDon", hoaDon.get());

        listHoaDon = hoaDonRepository.findAll();
        listCTSP = sanPhamChiTietRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();

        model.addAttribute("listHoaDon", listHoaDon);
        model.addAttribute("listCTSP", listCTSP);
        model.addAttribute("listHDCT", listHDCT);
        model.addAttribute("listKH", listKH);

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
        return "/view/view_sang/banHangTaiQuay.jsp";
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
        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
    }

    @PostMapping("giam-so-luong/{idCTSP}")
    public String giamSoLuong(@PathVariable String idCTSP, @RequestParam String idHoaDon) {
        //Giảm số lượng sản phẩm -1 khi ấn vào button giảm trong giỏ hàng
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
        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
    }

    //Xóa sản phẩm trong giỏ hàng
    @GetMapping("/delete/{idHDChiTiet}")
    public String deleteHDCT(@PathVariable String idHDChiTiet,
                             @RequestParam String idHoaDon) {
        //Lấy ra số lượng chi tiết  hóa đơn
        ChiTietHoaDon chiTietHoaDon = hoaDonChiTietRepository.findById(idHDChiTiet).get();
        Integer soLuongCTHD = chiTietHoaDon.getSoLuong();

        //Xóa spct trong giỏ hàng
        hoaDonChiTietRepository.deleteById(idHDChiTiet);

        //Sửa lại số lượng sản phẩm trong bảng chi tiết sản phẩm
        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(chiTietHoaDon.getIdCTSP().getId());
        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() + soLuongCTHD);
        sanPhamChiTietRepository.save(chiTietSanPham);

        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
    }


    @PostMapping("add-san-pham/{idCTSP}")
    public String addSanPhamVaoGioHang(@PathVariable String idCTSP,
                                       @RequestParam String idHoaDon, RedirectAttributes redirectAttributes) {
        ChiTietHoaDon hdct = new ChiTietHoaDon();

        //Tìm sản phẩm trong giỏ hàng
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

        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
    }

    //Thanh toán
    @PostMapping("/thanh-toan/{idHoaDon}")
    public String thanhToanSanPham(@PathVariable String idHoaDon,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao,
                                   @RequestParam String idKhachHang) {

        for (int i = 0; i < listHoaDon.size(); i++) {
            if (listHoaDon.get(i).getId().equals(idHoaDon)) {
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
    public void capMhatSoLuong() {
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


    //lọc sản phẩm chi tiết
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
////        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findAlll(idSanPham, idMauSac, idKichThuoc, idChatLieu, idKieuTay, pageable);
//
//
//        if (idSanPham!=null && idMauSac==null || idKichThuoc==null || idChatLieu==null || idKieuTay==null){
//            Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdSanPham(idSanPham, SPCTRepository.ACTIVE, pageable);
//            model.addAttribute("listCTSP",listCTSP);
//        } else if (idSanPham==null && idMauSac!=null && idKichThuoc==null && idChatLieu==null && idKieuTay==null){
//            Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdMauSac(idMauSac, SPCTRepository.ACTIVE, pageable);
//            model.addAttribute("listCTSP",listCTSP);
//        } else if (idSanPham==null && idMauSac==null && idKichThuoc!=null && idChatLieu==null && idKieuTay==null){
//            Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdKichThuoc(idKichThuoc, SPCTRepository.ACTIVE, pageable);
//            model.addAttribute("listCTSP",listCTSP);
//        } else if (idSanPham==null && idMauSac==null && idKichThuoc==null && idChatLieu!=null && idKieuTay==null){
//            Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdChatLieu(idChatLieu, SPCTRepository.ACTIVE, pageable);
//            model.addAttribute("listCTSP",listCTSP);
//        } else if (idSanPham==null && idMauSac==null && idKichThuoc==null && idChatLieu==null && idKieuTay!=null){
//            Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.locCTSPByIdKieuTay(idKieuTay, SPCTRepository.ACTIVE, pageable);
//            model.addAttribute("listCTSP",listCTSP);
//        }
//
//        listHoaDon = hoaDonRepository.findAll();
//        listHDCT = hoaDonChiTietRepository.findAll();
//        listKH = khachHangRepository.findAll();
//        model.addAttribute("listHoaDon",listHoaDon);
////        model.addAttribute("listCTSP",listCTSP);
//        model.addAttribute("listKH",listKH);
//        return "/view/view_viet/banHangTaiQuay.jsp";
//    }


}
