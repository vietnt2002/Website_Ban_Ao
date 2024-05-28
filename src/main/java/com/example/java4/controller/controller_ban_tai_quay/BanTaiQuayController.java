package com.example.java4.controller.controller_ban_tai_quay;

import com.example.java4.entities.HDCT;
import com.example.java4.entities.HoaDon;
import com.example.java4.entities.KhachHang;
import com.example.java4.entities.SPCT;
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
public class BanTaiQuayController {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HDCTRepository hoaDonChiTietRepository;

    @Autowired
    private SPCTRepository sanPhamChiTietRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    private List<HoaDon> listHoaDon;
    private List<HDCT> listHDCT;
    private List<SPCT> listCTSP;
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
        return "/ban-hang/index";
    }

    @PostMapping("add-hoa-don")
    public String themHoaDon(){
        HoaDon hoaDon = new HoaDon();

//        KhachHang khachHang = new KhachHang();
//        khachHang.setId(1);
//        hoaDon.setIdKhachHang(khachHang);
        hoaDon.setTrangThai(0);
        hoaDon.setNgayMuaHang(new Date());

        hoaDonRepository.save(hoaDon);

        return "redirect:/ban-hang";
    }

    @GetMapping("detail-hoa-don/{idHD}")
    public String detailHoaDon(@PathVariable Integer idHD, Model model){
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
        List<HDCT> gioHangTheoHoaDon = new ArrayList<>();
        for (HDCT ct:listHDCT){
            if (ct.getHoaDon().getId()==idHD){
                gioHangTheoHoaDon.add(ct);

                //Tính tổng tiền từng hóa đơn
                BigDecimal tongTien = BigDecimal.ZERO;
                for (HDCT hd:gioHangTheoHoaDon){
                    int sL = hd.getSoLuong();
                    BigDecimal donGia = BigDecimal.valueOf(hd.getDonGia());
                    BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(sL));
                    tongTien = tongTien.add(thanhTien);
                }
                model.addAttribute("tongTien",tongTien);
                model.addAttribute("listHDCT",gioHangTheoHoaDon);
            }
        }


        return "/ban-hang/index";
    }

    @PostMapping("add-san-pham/{idCTSP}")
    public String addSanPhamVaoGioHang(@PathVariable Integer idCTSP,
                                       @RequestParam Integer idHoaDon){
        HDCT hdct = new HDCT();

        //Tìm sản phẩm trong giỏ hàng
        boolean spTonTaiTrongGioHang = false;
        Integer slBanDau = 1;
        for (HDCT sp:listHDCT){
            if (sp.getSpct().getId()==idCTSP && sp.getHoaDon().getId()==idHoaDon){
                System.out.println("idCTSP"+sp.getSpct().getId());
                System.out.println("ID hóa đơn"+sp.getHoaDon().getId());

                sp.setSoLuong(sp.getSoLuong()+1);
                hoaDonChiTietRepository.save(sp);

//                slBanDau = sp.getSoLuongMua();
                spTonTaiTrongGioHang=true;
                System.out.println("-------Số lượng sản phẩm----------"+sp.getSoLuong());
                System.out.println("-------Số lượng sản test----------"+slBanDau);
                break;
            }
        }

        Double donGia = null;
        if (!spTonTaiTrongGioHang){
            for (SPCT sp:listCTSP){
                if (sp.getId()==idCTSP){
                    donGia = sp.getDonGia();
                }
            }

            SPCT ctsp = new SPCT();
            ctsp.setId(idCTSP);
            hdct.setSpct(ctsp);

            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(idHoaDon);
            hdct.setHoaDon(hoaDon);
            hdct.setSoLuong(1);
            hdct.setDonGia(donGia);

            hoaDonChiTietRepository.save(hdct);

        }

        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
    }

    @PostMapping("/delete-hdct/{idHDCT}")
    public String deleteHDCT(@PathVariable Integer idHDCT,
                             @RequestParam Integer idHoaDon){

        for (HDCT hdct:listHDCT){
            if (hdct.getId()==idHDCT){
                hoaDonChiTietRepository.delete(hdct);
            }
        }

        return "redirect:/ban-hang/detail-hoa-don/" + idHoaDon;
    }

    //Thanh toán
    @PostMapping("/thanh-toan/{idHoaDon}")
    public String thanhToanSanPham(@PathVariable Integer idHoaDon,
                                   @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date ngayTao,
                                   @RequestParam Integer idKhachHang){

        for (int i=0;i<listHoaDon.size();i++){
            if (listHoaDon.get(i).getId()==idHoaDon){
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(idHoaDon);
                KhachHang khachHang = new KhachHang();
                khachHang.setId(idKhachHang);
                hoaDon.setKhachHang(khachHang);
                capMhatSoLuong();
                hoaDon.setTrangThai(1);
                hoaDon.setNgayMuaHang(ngayTao);
                hoaDonRepository.save(hoaDon);
            }
        }

        return "redirect:/ban-hang";
    }

    //Cập nhật só lượng sau khi thanh toán
    public void capMhatSoLuong(){
        for (HDCT hdct:listHDCT){
            int idSPCT = hdct.getSpct().getId();
            int soLuong = hdct.getSoLuong();
            for (SPCT sp:listCTSP){
                if (sp.getId()==idSPCT){
                    sp.setSoLuong(sp.getSoLuong()-soLuong);
                    sanPhamChiTietRepository.save(sp);
                }
            }
        }
    }

}
