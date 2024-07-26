package com.example.java4.controller.controller_viet;

import com.example.java4.config.UserInfor;
import com.example.java4.entities.*;
import com.example.java4.repositories.*;
import com.example.java4.repositories.viet_repo.CTHDRepository;
import com.example.java4.repositories.viet_repo.CTSPRepository;
import com.example.java4.request.req_tai.KhachHangDTO;
import com.example.java4.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("store")
public class FilterTrangChuController {
    @Autowired
    SPCTRepository spctRepo;
    @Autowired
    HinhAnhRepository hinhAnhRepo;
    @Autowired
    KichThuocRepository kichThuocRepo;
    @Autowired
    MauSacRepository mauSacRepo;
    @Autowired
    KieuTayRepository kieuTayRepo;
    @Autowired
    KhachHangRepository khachHangRepo;
    @Autowired
    HoaDonRepository hoaDonRepo;
    @Autowired
    HDCTRepository hdctRepo;
    @Autowired
    SanPhamRepository sanPhamRepo;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    GiaoHangRepo giaoHangRepo;
    @Autowired
    DiaChiRepository diaChiRepo;
    @Autowired
    KhuyenMaiRepository khuyenMaiRepo;

    @Autowired
    CTSPRepository ctspRepo;

    @Autowired
    CTHDRepository cthdRepo;

    private List<HoaDon> listHoaDon = new ArrayList<>();
    private List<ChiTietHoaDon> listHDCT = new ArrayList<>();
    private List<ChiTietSanPham> listCTSP = new ArrayList<>();
    private List<SanPham> listSanPham = new ArrayList<>();
    private List<KhachHang> listKhachHang = new ArrayList<>();
    private List<GioHangResponse> listGioHang;
    private List<DiaChi> listDiaChi = new ArrayList<>();
    private List<KhuyenMai> listKhuyenMai = new ArrayList<>();
    private List<HoaDonResponse> listHDByIDKH = new ArrayList<>();

    @GetMapping("/trang-chu")
    public String getTrangChu(
            Model model,
            @RequestParam("page") Optional<Integer> pageParam
    ) {
        if (!model.containsAttribute("khachHangDTO")) {
            System.out.println("crette dto");
            model.addAttribute("khachHangDTO", new KhachHangDTO());
        }
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 2);
        List<SPCTResponse> listSanPhamRes = ctspRepo.getAllSP(pageable);
        model.addAttribute("listSanPhamRes", listSanPhamRes);
        model.addAttribute("soLuong", hdctRepo.findByKHnStt(khachHangRepo.findByIdKH(UserInfor.idKhachHang)));
        //Tính tổng số lượng sản phẩm có trong giỏ hàng
        listHDCT = hdctRepo.findByIdHoaDonByIDKH(UserInfor.idKhachHang, HoaDonRepository.CHO_THANH_TOAN);
        listGioHang = hdctRepo.getAll(UserInfor.idKhachHang, HoaDonRepository.CHO_THANH_TOAN);
        Integer totalSoLuong = 0;
        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
            totalSoLuong += chiTietHoaDon.getSoLuong();
        }
        model.addAttribute("soLuong", totalSoLuong);

        BigDecimal minGiaBan = ctspRepo.minGiaBan();
        BigDecimal maxGiaBan = ctspRepo.maxGiaBan();
        model.addAttribute("minGiaBan", minGiaBan);
        model.addAttribute("maxGiaBan", maxGiaBan);

        model.addAttribute("listMauSac", mauSacRepo.findAll());
        model.addAttribute("listKichThuoc", kichThuocRepo.findAll());
        model.addAttribute("listKieuTay", kieuTayRepo.findAll());

        //Lấy ra danh sách chi tiết sản phẩm response
        model.addAttribute("listCTSPRes", ctspRepo.getCTSPRes());

        //Lấy ra danh sách chi tiết hóa đơn
        model.addAttribute("listCTHDByTT", cthdRepo.getAllCTHDByTrangThai(HDCTRepository.ACTIVE));
        return "/view/view_viet/trangChu.jsp";
    }

    //Hiển thị chi tiết sản phẩm đã chọn
    @GetMapping("detail-san-pham/{idCTSP}")
    public String detail(
            Model model,
            @PathVariable("idCTSP") String idCTSP
    ) {
        ChiTietSanPham chiTietSanPham = spctRepo.findById(idCTSP).get();
        HinhAnh hinhAnh = hinhAnhRepo.findByIdCTSP(idCTSP);

        //Lấy danh sách màu sắc, kích thước, số lượng của sản phẩm
        List<MauSizeSL> listMauSizeSL = spctRepo.getListMauSizeSL(chiTietSanPham.getIdSanPham().getId());

        //Lấy ra danh sách kích thước theo idSP
        List<KichThuocRespone> listKichThuoc = kichThuocRepo.getListKichThuocByIdSP(chiTietSanPham.getIdSanPham().getId());

        //Lấy danh sách màu sắc theo idSP
        List<MauSacRespone> listMauSac = mauSacRepo.getListMauSacByIdSP(chiTietSanPham.getIdSanPham().getId());

        //Lấy số lượng spct của thằng đầu tiên khi vào trang
        KichThuocRespone kichThuocRespone = listKichThuoc.get(0);
        MauSacRespone mauSacRespone = listMauSac.get(0);

        Integer soLuongTon = spctRepo.getSoLuongCTSPBySanPham_MauSac_KichThuoc(chiTietSanPham.getIdSanPham().getId(), mauSacRespone.getIdMS(), kichThuocRespone.getIdKth());

        model.addAttribute("soLuongTon", soLuongTon);
        model.addAttribute("ctsp", chiTietSanPham);
        model.addAttribute("hinhAnh", hinhAnh);
        model.addAttribute("listKichThuoc", listKichThuoc);
        model.addAttribute("listMauSizeSL", listMauSizeSL);
        model.addAttribute("listMauSac", listMauSac);
        model.addAttribute("soLuong", hdctRepo.findByKHnStt(khachHangRepo.findByIdKH(UserInfor.idKhachHang)));

        //Tính tổng số lượng sản phẩm có trong giỏ hàng
        listHDCT = hdctRepo.findByIdHoaDonByIDKH(UserInfor.idKhachHang, HoaDonRepository.CHO_THANH_TOAN);
        listGioHang = hdctRepo.getAll(UserInfor.idKhachHang, HoaDonRepository.CHO_THANH_TOAN);
        Integer totalSoLuong = 0;
        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
            totalSoLuong += chiTietHoaDon.getSoLuong();
        }
        model.addAttribute("soLuong", totalSoLuong);
        return "/view/BanHangOnline/chiTietSanPham.jsp";
    }

}
