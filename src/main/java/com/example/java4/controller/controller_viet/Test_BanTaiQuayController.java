package com.example.java4.controller.controller_viet;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.entities.HoaDon;
import com.example.java4.entities.KhachHang;
import com.example.java4.repositories.HDCTRepository;
import com.example.java4.repositories.HoaDonRepository;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.SPCTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ban-hang")
public class Test_BanTaiQuayController {

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

    @GetMapping("")
    public String hienThi(Model model){
        listHoaDon = hoaDonRepository.findAll();
        listCTSP = sanPhamChiTietRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();
        model.addAttribute("listHoaDon",listHoaDon);
        model.addAttribute("listCTSP",listCTSP);
//        model.addAttribute("listHDCT",listHDCT);
        model.addAttribute("listKH",listKH);
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

        return "redirect:/ban-hang";
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


        return "/view/view_ban_tai_quay/index";
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

}
