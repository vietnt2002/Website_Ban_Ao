package com.example.java4.controllers;

import com.example.java4.dto.sale.NewHDCTRequest;
import com.example.java4.entities.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.ArrayList;

@Controller
@RequestMapping("sale")
public class SaleController {
    //    @RequestMapping(name="login", method = RequestMethod.POST)
    ArrayList<SPCT> dsSPCT;
    ArrayList<KichThuoc> dsKichThuoc;
    ArrayList<MauSac> dsMauSac;
    ArrayList<SanPham> dsSanPham;
    ArrayList<HoaDon> dsHoaDon;
    ArrayList<HDCT> dsHDCTBucket;
    ArrayList<HDCT> dsHDCT;
    ArrayList<NhanVien> dsNhanVien;
    ArrayList<KhachHang> dsKhachHang;
    HoaDon hoaDonTemp;
    String paymentStt;
    KhachHang kh;
    NhanVien nv;

    public ArrayList<KichThuoc> loadToKichThuocCbo() {
        ArrayList<KichThuoc> lstKichThuoc = new ArrayList<>();
        lstKichThuoc.add(new KichThuoc(1, "kt1", "large", 1));
        lstKichThuoc.add(new KichThuoc(2, "kt2", "min", 1));
        lstKichThuoc.add(new KichThuoc(3, "kt3", "medium", 1));
        lstKichThuoc.add(new KichThuoc(4, "kt4", "average", 2));
        lstKichThuoc.add(new KichThuoc(5, "kt5", "oversize",1 ));
        return lstKichThuoc;
    }

    public ArrayList<MauSac> loadMauSacCbo() {
        ArrayList<MauSac> lstMauSac = new ArrayList<>();
        lstMauSac.add(new MauSac(1, "bl1", "Black",1));
        lstMauSac.add(new MauSac(2, "Vi1", "Violet",2));
        lstMauSac.add(new MauSac(3, "Ye1", "Yellow",1));
        lstMauSac.add(new MauSac(4, "Pi1", "Pink",2));
        lstMauSac.add(new MauSac(5, "Gry1", "Gray",3));
        return lstMauSac;
    }

    public ArrayList<SanPham> loadSanPhamCbo() {
        ArrayList<SanPham> lstSanPham = new ArrayList<>();
        lstSanPham.add(new SanPham(1, "SP1", "Wallet", "Stocking"));
        lstSanPham.add(new SanPham(2, "SP2", "Learther strap apple watch", "Stocking"));
        lstSanPham.add(new SanPham(3, "SP3", "Clutch", "Stocking"));
        lstSanPham.add(new SanPham(4, "SP4", "Belt", "Stocking"));
        lstSanPham.add(new SanPham(5, "SP5", "Leather case", "Stocking"));
        lstSanPham.add(new SanPham(6, "SP6", "Sticky leather(optional size)", "Stocking"));
        return lstSanPham;
    }

    public ArrayList<KhachHang> loadDsKhachHang() {
        ArrayList<KhachHang> lstKhachHang = new ArrayList<>();
        lstKhachHang.add(new KhachHang(1, "Nguyễn Thị Linh","kh001", "0333336666", 1));
        lstKhachHang.add(new KhachHang(2, "Nguyen Hữu Lộc","kh002", "0333322222", 0));
        lstKhachHang.add(new KhachHang(3, "Phan Đắc Quang","kh003", "0333334535", 1));
        lstKhachHang.add(new KhachHang(4, "Phạm Thị Lan","kh004", "0333335353", 0));
        lstKhachHang.add(new KhachHang(5, "Nguyễn Phi Hùng","kh005", "0333335353", 1));
        lstKhachHang.add(new KhachHang(6, "Vũ Trọng Tài","kh006", "0333338878", 1));
        return lstKhachHang;
    }

    public ArrayList<NhanVien> loadDsNhanVien() {
        ArrayList<NhanVien> lstNhanVien = new ArrayList<>();
        lstNhanVien.add(new NhanVien(1, "Nguyễn Phúc Lộc", "nv1", "phuclocub", "loc123", 1));
        lstNhanVien.add(new NhanVien(2, "Phạm Thị Linh", "nv2", "linhnt01", "linh123", 2));
        lstNhanVien.add(new NhanVien(3, "Nguyễn Thị Lan", "nv3", "lanvt02", "lan123", 1));
        lstNhanVien.add(new NhanVien(4, "Nguyễn Văn Hùng", "nv4", "hungtt05", "hung123", 1));
        lstNhanVien.add(new NhanVien(5, "Nguyễn Tuấn Tài", "nv5", "tailv06", "tai123", 0));
        return lstNhanVien;
    }

    public ArrayList<HoaDon> loadHoaDon() {
        ArrayList<HoaDon> lstHoaDon = new ArrayList<>();
        dsNhanVien = loadDsNhanVien();
        dsKhachHang = loadDsKhachHang();
//        lstHoaDon.add(new HoaDon("1", dsNhanVien.get(1), dsKhachHang.get(1), new Date(System.currentTimeMillis())));
//        lstHoaDon.add(new HoaDon("2", dsNhanVien.get(2), dsKhachHang.get(2), new Date(System.currentTimeMillis())));
//        lstHoaDon.add(new HoaDon("3", dsNhanVien.get(3), dsKhachHang.get(3), new Date(System.currentTimeMillis())));
//        lstHoaDon.add(new HoaDon("4", dsNhanVien.get(1), dsKhachHang.get(2), new Date(System.currentTimeMillis())));
//        lstHoaDon.add(new HoaDon("5", dsNhanVien.get(2), dsKhachHang.get(1), new Date(System.currentTimeMillis())));
//        lstHoaDon.add(new HoaDon("6", dsNhanVien.get(3), dsKhachHang.get(2), new Date(System.currentTimeMillis())));
        return lstHoaDon;
    }

    public ArrayList<HDCT> getHDCTByIDHoaDon(String idHD) {
        dsHDCTBucket = new ArrayList<>();
        if (dsHDCT!=null) {
            for (int i = 0; i < dsHDCT.size(); i++) {
                if (dsHDCT.get(i).getHd().getId().equals(idHD)) {
                    dsHDCTBucket.add(dsHDCT.get(i));
                }
            }
        }
        System.out.println(dsHDCTBucket.size());
        return dsHDCTBucket;
    }

    public void addTodsHDCT(SPCT spct){
        HDCT hdct = new HDCT(String.valueOf(dsHDCT.size()+1),hoaDonTemp,spct,1,spct.getDonGia(),"1");
        dsHDCT.add(hdct);
        System.out.println(dsHDCT.size());
    }

    public HDCT reqToModel(NewHDCTRequest req) {
        HoaDon newHoaDon = new HoaDon();
        SPCT newSPCT = new SPCT();
        HDCT newHDCT = new HDCT();
        for (int i = 0; i < dsHDCT.size(); i++) {
            if (dsHDCT.get(i).getId().equals(req.getIdHD())) {
                newHoaDon = dsHDCT.get(i).getHd();
            }
        }

        for (int i = 0; i < dsSPCT.size(); i++) {
            if (dsSPCT.get(i).getId().equals(req.getIdSPCT())) {
                newHoaDon = dsHDCT.get(i).getHd();
            }
        }
        newHDCT = new HDCT(req.getId(), newHoaDon, newSPCT, req.getSoLuong(), req.getDonGia(), req.getTrangThai());
        return newHDCT;
    }

    public SaleController() {
        this.dsSPCT = new ArrayList<>();
        this.dsHDCT  = new ArrayList<>();
        this.dsHDCTBucket = new ArrayList<>();
        this.dsKichThuoc = loadToKichThuocCbo();
        this.dsMauSac = loadMauSacCbo();
        this.dsSanPham = loadSanPhamCbo();
        this.dsHoaDon = loadHoaDon();
        this.hoaDonTemp = new HoaDon();
        this.kh = dsKhachHang.get(1);
        this.nv = dsNhanVien.get(2);
        this.paymentStt = "not yet";
//        dsSPCT.add(new SPCT(1, "SPCT1", dsKichThuoc.get(1), dsMauSac.get(1), dsSanPham.get(1), 1, 150.5, "stocking"));
//        dsSPCT.add(new SPCT(2, "SPCT2", dsKichThuoc.get(2), dsMauSac.get(2), dsSanPham.get(2), 3, 250.5, "stocking"));
//        dsSPCT.add(new SPCT(3, "SPCT3", dsKichThuoc.get(3), dsMauSac.get(3), dsSanPham.get(3), 5, 50.5, "stocking"));
//        dsSPCT.add(new SPCT(4, "SPCT4", dsKichThuoc.get(4), dsMauSac.get(4), dsSanPham.get(4), 6, 100.5, "stocking"));
//        dsSPCT.add(new SPCT(5, "SPCT5", dsKichThuoc.get(2), dsMauSac.get(1), dsSanPham.get(3), 7, 120.5, "stocking"));
//        dsSPCT.add(new SPCT(6, "SPCT6", dsKichThuoc.get(4), dsMauSac.get(3), dsSanPham.get(2), 3, 110.5, "stocking"));
    }

    public void initHDCT(HoaDon hd) {
        if(hd.getId()!=null) {
            HDCT newHdct = new HDCT(String.valueOf(dsHDCT.size() + 1), hd, null, 0, 0.0, "not yet");
            dsHDCT.add(newHdct);
        }
    }

    public void firstInitHDCT(HoaDon hd) {
        if(hd.getId()!=null) {
            HDCT newHdct = new HDCT(String.valueOf(1), hd, null, 0, 0.0, "not yet");
            dsHDCT.add(newHdct);
        }
    }


    @GetMapping("/create")
    public String create(Model model) {
        if (hoaDonTemp.getId() != null && dsHDCT != null) {
            int flag = 0;
            for (int i = 0; i < dsHDCT.size(); i++) {
                if (dsHDCT.get(i).getHd().equals(hoaDonTemp)) {
                    System.out.println("dupplicate");
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("inint new hdct record");
                initHDCT(hoaDonTemp);
            }
        } else if(dsHDCT==null&&hoaDonTemp.getId()!=null) {
            System.out.println("init new hdct record 2nd");
            firstInitHDCT(hoaDonTemp);
        }
        if(hoaDonTemp.getId()!=null)
        paymentStt = getHDCTByIDHoaDon(hoaDonTemp.getId()).get(0).getTrangThai();
        model.addAttribute("hoaDon",hoaDonTemp);
        model.addAttribute("dsSPCT", dsSPCT);
        model.addAttribute("dsHoaDon", dsHoaDon);
        model.addAttribute("dsHDCT", getHDCTByIDHoaDon(hoaDonTemp.getId()));
        model.addAttribute("trangThai",paymentStt);
        return "admin/sale/MainView";
    }

    @GetMapping("/addNewHD")
    public String addNewHD(Model model) {
//        dsHoaDon.add(new HoaDon(String.valueOf(dsHoaDon.size()+1),nv,kh, new Date(System.currentTimeMillis())));
        return "redirect:/sale/create";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(Model model,@PathVariable(value = "id")String id) {
        System.out.println("spct"+ id);
        if(hoaDonTemp.getId()!=null){
            for (int i = 0; i < dsSPCT.size() ; i++) {
                if(dsSPCT.get(i).getId().equals(id)){
                    System.out.println("found spct");
                    addTodsHDCT(dsSPCT.get(i));
                    break;
                }
            }
        }
        return "redirect:/sale/create";
    }

    @GetMapping("/check/{id}")
    public String check(Model model,@PathVariable(value = "id")String id) {
        System.out.println("spct"+ id);
        if(hoaDonTemp.getId()!=null){
            for (int i = 0; i < dsHDCT.size(); i++) {
                if(dsHDCT.get(i).getHd().getId().equals(id)){
                    dsHDCT.get(i).setTrangThai("checked");
                }
            }
        }
        return "redirect:/sale/create";
    }


    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("data", dsSPCT);
        return "admin/ql_spct/Index";
    }

    @GetMapping("/select/{id}")
    public String index(Model model, @PathVariable(value = "id") String id) {
        System.out.println("hoa don:"+id);
        for (int i = 0; i < dsHoaDon.size(); i++) {
            if (dsHoaDon.get(i).getId().equals(id)) {
                hoaDonTemp = dsHoaDon.get(i);
                break;
            }
        }
        return "redirect:/sale/create";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") String id) {
        for (int i = 0; i < dsSPCT.size(); i++) {
            if (dsSPCT.get(i).getId().equals(id)) {
                dsSPCT.remove(i);
            }
        }
        return "redirect:/spct/index";
    }

//    @GetMapping("/update/{id}")
//    public String getUpdate(Model model, @PathVariable(value = "id") String id) {
//        model.addAttribute("dsKichThuoc", dsKichThuoc);
//        model.addAttribute("dsMauSac", dsMauSac);
//        model.addAttribute("dsSanPham", dsSanPham);
//        SPCT spct = new SPCT();
//        for (int i = 0; i < dsSPCT.size(); i++) {
//            if (dsSPCT.get(i).getId().equals(id)) {
//                spct = dsSPCT.get(i);
//            }
//        }
//        StoreRequest newReq = new StoreRequest(spct.getId(), spct.getMaSPCT(), spct.getKichThuoc().getId(), spct.getMauSac().getId(), spct.getSanPham().getId(), spct.getSoLuong(), spct.getDonGia(), spct.getTrangThai());
//        model.addAttribute("data", newReq);
//        return "admin/ql_spct/Edit";
//    }

//    @PostMapping("/update/{id}")
//    public String doUpdate(@Valid @ModelAttribute("data") StoreRequest req, BindingResult result, @PathVariable(value = "id") String id, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("dsKichThuoc", dsKichThuoc);
//            model.addAttribute("dsMauSac", dsMauSac);
//            model.addAttribute("dsSanPham", dsSanPham);
//            return "admin/ql_spct/Edit";
//        } else {
//            SPCT spct = reqToModel(req);
//            for (int i = 0; i < dsSPCT.size(); i++) {
//                if (dsSPCT.get(i).getId().equals(id)) {
//
//                }
//            }
//            rem = req;
//            return "redirect:/spct/index";
//        }
//    }

    @PostMapping("store")
    public String Store(
            @Valid @ModelAttribute("data") NewHDCTRequest req,
            BindingResult result, Model model
    ) {
        dsHDCT.add(reqToModel(req));
        model.addAttribute("dsSPCT", dsSPCT);
        model.addAttribute("dsHD", dsHoaDon);
        model.addAttribute("dsHDCT", getHDCTByIDHoaDon(hoaDonTemp.getId()));
        return "redirect:/spct/create";
    }
}