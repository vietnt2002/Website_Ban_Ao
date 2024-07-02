package com.example.java4.controller.BanHangTaiQuay;

import com.example.java4.config.UserInfor;
import com.example.java4.entities.*;
import com.example.java4.repositories.*;
import com.example.java4.request.dangNhap.NVSignUpRequest;
import com.example.java4.request.req_viet.NhanVienRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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

    @Autowired
    KhuyenMaiRepository khuyenMaiRepo;
    @Autowired
    Validator validator;

    private List<KhuyenMai> listKhuyenMai;
    private List<HoaDon> listHoaDon;
    private List<ChiTietHoaDon> listHDCT;
    private List<ChiTietSanPham> listCTSP;
    private List<KhachHang> listKH;
    private List<MauSac> listMauSac;
    private List<KichThuoc> listKichThuoc;
    private List<SanPham> listSanPham;
    private List<KieuTay> listKieuTay;
    private List<ChatLieu> listChatLieu;
    private String idNV = null;
    private String idHoaDon;

    private int tongSL;

//    private BigDecimal tongTien;

    public BanTaiQuayController() {
        idHoaDon = "";
        tongSL = 0;
//        tongTien = BigDecimal.ZERO;
    }
//    @GetMapping("dang-nhap-view")
//    public String getDangNhapview(Model model){
//        NVSignUpRequest nhanVienRequest = new NVSignUpRequest();
//        model.addAttribute("nhanVien", nhanVienRequest);
//        return "/view/BanHangTaiQuay/dangNhapAdmin.jsp";
//    }
//
//    @PostMapping("dang-nhap")
//    public String dangNhap(
//            Model model,
//            @Valid @ModelAttribute("nhanVien") NVSignUpRequest nvReq,
//            BindingResult result,
//            RedirectAttributes redirectAttributes,
//            HttpSession session
//    ){
//        if (result.hasErrors()){
//            System.out.println("Có lỗi");
//            System.out.println("result error:"+result.toString());
//            return "/view/BanHangTaiQuay/dangNhapAdmin.jsp";
//        }
//
//        //Tìm kiếm nhân viên theo tên tài khoản
//        boolean checkRole = false;
//        NhanVien nhanVienByTK = nhanVienRepo.findByTaiKhoan(nvReq.getTaiKhoan());
//        if (nhanVienByTK == null){
//            redirectAttributes.addFlashAttribute("error", "Tên tài khoản không tồn tại!");
//            return "redirect:/admin/dang-nhap-view";
//        }else {
//            if (nvReq.getMatKhau().equals(nhanVienByTK.getMatKhau())){
//                UserInfor.idNhanVien = nhanVienByTK.getId();
//                //Check admin hay nhân viên
//                String role = nhanVienByTK.getIdCV().getTen();
//                session.setAttribute("userRole", role);
//
//                redirectAttributes.addFlashAttribute("success", "Đăng nhập thành công");
//                return "redirect:/ban-hang-tai-quay";
//            }else {
//                redirectAttributes.addFlashAttribute("error", "Mật khẩu nhập vào chưa đúng!");
//                return "redirect:/admin/dang-nhap-view";
//            }
//        }
//    }

    @GetMapping("dang-xuat")
    public String dangXuat(){
        UserInfor.idNhanVien = null;
        return "redirect:/admin/dang-nhap-view";
    }

    @GetMapping("")
    public String hienThi(Model model, @RequestParam(value = "page",defaultValue ="0") String pageParam ) {
        //Hiển thị thông tin nhân viên đăng nhập
        if (UserInfor.idNhanVien != null){
            NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNhanVien).get();
            model.addAttribute("nv", nhanVien);
            System.out.println("========================================= test paa"+pageParam);
            Pageable pageable = PageRequest.of(Integer.valueOf(pageParam), 1000);
            listHoaDon = hoaDonRepository.selectTop5();
            Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findByTrangThai(1,pageable);
            listKH = khachHangRepository.findAll();
            listMauSac = mauSacRepository.findAll();
            listKichThuoc = kichThuocRepo.findAll();
            listKieuTay = kieuTayRepo.findAll();
            listSanPham = sanPhamRepo.findAll();
            listChatLieu = chatLieuRepo.findAll();
            listKhuyenMai = khuyenMaiRepo.findAll();
            model.addAttribute("listMauSac", listMauSac);
            model.addAttribute("listKichThuoc", listKichThuoc);
            model.addAttribute("listChatLieu", listChatLieu);
            model.addAttribute("listKieuTay", listKieuTay);
            model.addAttribute("listSanPham", listSanPham);
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listCTSP", listCTSP);
            model.addAttribute("listKH", listKH);
            model.addAttribute("listKM",listKhuyenMai);
            model.addAttribute("tongSL", tongSL);
            System.out.println(listMauSac);
            return "/view/BanHangTaiQuay/banHangTaiQuay.jsp";
        }
        else{
            return  "redirect:/admin/dang-nhap-view";
        }
    }
    @GetMapping("detail-hoa-don/{idHD}")
    public String detailHoaDon(@PathVariable String idHD, @RequestParam Optional<Integer> pageParam,
                               @RequestParam("page") Optional<Integer> pageParam2, Model model) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        idHoaDon = idHD;
        NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNhanVien).get();
        model.addAttribute("nv", nhanVien);
        model.addAttribute("hoaDon", hoaDon.get());
        listHoaDon = hoaDonRepository.selectTop5();
//        listHDCT = hoaDonChiTietRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAllByHoaDon_Id(idHoaDon);
        listKH = khachHangRepository.findAll();
        listMauSac = mauSacRepository.findAll();
        listKichThuoc = kichThuocRepo.findAll();
        listKieuTay = kieuTayRepo.findAll();
        listSanPham = sanPhamRepo.findAll();
        listChatLieu = chatLieuRepo.findAll();
        listKhuyenMai = khuyenMaiRepo.findAll();
        int page = pageParam2.orElse(0);
        Pageable p = PageRequest.of(page,100);
        Page<KhachHang> pageData = khachHangRepository.findByTrangThai(khachHangRepository.ACTIVE,p);
        model.addAttribute("pageData",pageData);
        int total = pageData.getTotalPages();
        model.addAttribute("total",total);
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 1000);
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
        model.addAttribute("listKM",listKhuyenMai);
        BigDecimal tongTien2 = hoaDonChiTietRepository.tinhGiaTriHD(idHoaDon);
        model.addAttribute("total",tongTien2);
        return "/view/BanHangTaiQuay/banHangTaiQuay.jsp";
    }


    @PostMapping("/delete-hdct/{idHDCT}/{idCTSP}")
    public String deleteHDCT(@PathVariable String idHDCT,@PathVariable String idCTSP){
        HoaDon hd = new HoaDon();
        int count = 0;
        ChiTietSanPham ctsp = sanPhamChiTietRepository.findByIdCTSP(idCTSP);
        for (ChiTietHoaDon hdct:listHDCT){
            if (hdct.getId().equals(idHDCT)){
                count=hdct.getSoLuong();
                hoaDonChiTietRepository.delete(hdct);
                hd = hdct.getIdHoaDon();

                if (ctsp.getId().equals(idCTSP)){
                    ctsp.setSoLuong(ctsp.getSoLuong()+count);
                    sanPhamChiTietRepository.save(ctsp);
                }
            }
        }

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
        Optional<NhanVien> nv = nhanVienRepo.findById(UserInfor.idNhanVien);
        hoaDon.setIdNhanVien(nv.get());
        hoaDon.setMa(ma);
        hoaDon.setTrangThai(0);
        hoaDon.setLoaiHoaDon(0);
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

    //  Delete hóa đơn
    @GetMapping("delete-hoa-don/{idHoaDon}")
    public String deleteHoaDon(@PathVariable String idHoaDon){

        for (HoaDon hoaDon: listHoaDon){
            if (hoaDon.getId().equals(idHoaDon)){
                hoaDonRepository.delete(hoaDon);
            }
        }
        return "redirect:/ban-hang_tai_quay";
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

    //Cập nhật số lượng
    @PostMapping("update-sl/{idCTSP}")
    public String updateSoLuong(@PathVariable String idCTSP,
//                                @RequestBody Map<String, Integer> request,
                                @RequestParam int soLuong,
                                Model model){

        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(idCTSP);
//        int newQuantity = request.get("quantity");

        tongSL = chiTietSanPham.getSoLuong();
        for(ChiTietHoaDon chiTietHoaDon : listHDCT){
            if (chiTietHoaDon.getIdCTSP().getId().equals(idCTSP) && chiTietHoaDon.getIdHoaDon().getId().equals(idHoaDon)) {

                int sl = chiTietHoaDon.getSoLuong();
                chiTietHoaDon.setSoLuong(soLuong);

                hoaDonChiTietRepository.save(chiTietHoaDon);

                //Số lượng của sản phẩm chi tiết -1 khi ấn vào button thêm trong giỏ hàng
                if (chiTietSanPham.getId().equals(idCTSP) ) {
                    chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - chiTietHoaDon.getSoLuong() + sl);
                    sanPhamChiTietRepository.save(chiTietSanPham);
                }
            }

        }

        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
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


    @PostMapping("add-san-pham/{idCTSP}")
    public String addSanPhamVaoGioHang(@PathVariable String idCTSP, @RequestParam("page") Optional<Integer> pageParam,
                                       @RequestParam String idHoaDon, RedirectAttributes redirectAttributes) {
        ChiTietHoaDon hdct = new ChiTietHoaDon();
        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(idCTSP);
        tongSL = chiTietSanPham.getSoLuong();
        //Tìm sản phẩm trong giỏ hàng
        if(validator.isOutStock(1,idCTSP)){
            Pageable pageable = PageRequest.of(pageParam.orElse(0), 10);
            Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.findAll(pageable);
            boolean spTonTaiTrongGioHang = false;
            Integer slBanDau = 1;
            for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
                //Nếu số lượng trong spct = 0 thì không đưuọc thêm sản phẩm nữa
                if (chiTietSanPham.getSoLuong() <= 0) {
                    chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());
                    redirectAttributes.addFlashAttribute("error", "Tạm thời hết số lượng sản phẩm");
                    try {
                        hoaDonChiTietRepository.save(chiTietHoaDon);
                        spTonTaiTrongGioHang = true;
                        hdct.setSoLuong(hdct.getSoLuong()+1);
                        hoaDonChiTietRepository.save(hdct);
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
            BigDecimal donGia =  new BigDecimal(0);
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
        }
        else{
            redirectAttributes.addFlashAttribute("error", "Không đủ số lượng.");
        }
        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
    }
    @PostMapping("/thanh-toan/{idHoaDon}")
    public String thanhToanSanPham(@PathVariable("idHoaDon") HoaDon newHoaDon,
                                   @RequestParam String idKhuyenMai,
                                   @RequestParam String idKH,
                                   @RequestParam BigDecimal tongTien) {
        LocalDateTime now =LocalDateTime.now();
        System.out.println("=================id hoa don: "+idHoaDon);
        System.out.println("=================khuyen mai:"+ idKhuyenMai);
        System.out.println("=================id kh: "+ idKH);
        System.out.println("=================tong tien: "+ tongTien);
        System.out.println("===================true false:"+ idKhuyenMai.equals(""));
        newHoaDon.setIdNhanVien(nhanVienRepo.findById(UserInfor.idNhanVien).get());
        newHoaDon.setPhuongThucThanhToan(0);
        if(idKhuyenMai.equals("")){
            newHoaDon.setIdKhuyenMai(null);
        }
        else{
            newHoaDon.setIdKhuyenMai(khuyenMaiRepo.findById(idKhuyenMai).get());
        }
        if(idKH.equals("")){
            newHoaDon.setIdKhachHang(null);
        }
        else{
            newHoaDon.setIdKhachHang(khachHangRepository.findById(idKH).get());
        }
        newHoaDon.setNgayThanhToan(now);
        newHoaDon.setTongTien(tongTien);
        newHoaDon.setLoaiHoaDon(0);
        newHoaDon.setTrangThai(6);
        hoaDonRepository.save(newHoaDon);
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
    @PostMapping("searchSPCT/{idHD}")
    public String search(
            @PathVariable String idHD,
            Model model,
            @RequestParam("search") String search,
            @RequestParam("page") Optional<Integer> pageParam
    ){

        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        model.addAttribute("hoaDon",hoaDon.get());

        Pageable pageable = PageRequest.of(pageParam.orElse(0), 1000);
        Page<ChiTietSanPham> listCTSP = sanPhamChiTietRepository.timKiem(search, SPCTRepository.ACTIVE, pageable);
        listHoaDon = hoaDonRepository.findAll();
        listHDCT = hoaDonChiTietRepository.findAll();
        listKH = khachHangRepository.findAll();
        model.addAttribute("listHoaDon",listHoaDon);
        model.addAttribute("listCTSP",listCTSP);
        model.addAttribute("listKH",listKH);
        model.addAttribute("listSanPham",sanPhamRepo.findAll());
        model.addAttribute("listMauSac",mauSacRepository.findAll());
        model.addAttribute("listKichThuoc",kichThuocRepo.findAll());
        model.addAttribute("listChatLieu",chatLieuRepo.findAll());
        model.addAttribute("listKieuTay",kieuTayRepo.findAll());
//        return "/view/BanHangTaiQuay/ban-hang-tai-quay.jsp";
        return "/view/BanHangTaiQuay/index2.jsp";
    }


    //Lọc sản phẩm
    @GetMapping("locSPCTBySanPham/{idSanPham}")
    public String locSPCTBySanPham(
            Model model,
            @PathVariable("idSanPham") String idSanPham,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        if (idHoaDon.equals("")){
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
        }else {
            Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHoaDon);
            model.addAttribute("hoaDon",hoaDon.get());

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
        }

        return "/view/BanHangTaiQuay/index2.jsp";
    }

    //Lọc màu sắc
    @GetMapping("locSPCTByMauSac/{idMauSac}")
    public String locSPCTByMauSac(
            Model model,
            @PathVariable("idMauSac") String idMauSac,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        if (idHoaDon.equals("")){
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
        }else {
            Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHoaDon);
            model.addAttribute("hoaDon",hoaDon.get());

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
        }

        return "/view/BanHangTaiQuay/index2.jsp";
    }
    //Lọc kích thước
    @GetMapping("locSPCTByKichThuoc/{idKichThuoc}")
    public String locSPCTByKichThuoc(
            Model model,
            @PathVariable("idKichThuoc") String idKichThuoc,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        if (idHoaDon.equals("")){
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
        }else {

            Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHoaDon);
            model.addAttribute("hoaDon",hoaDon.get());

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
        }
        return "/view/BanHangTaiQuay/index2.jsp";
    }
    //Lọc chất liệu
    @GetMapping("locSPCTByChatLieu/{idChatLieu}")
    public String locSPCTByChatLieu(
            Model model,
            @PathVariable("idChatLieu") String idChatLieu,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        if (idHoaDon.equals("")){
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
        }else {
            Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHoaDon);
            model.addAttribute("hoaDon",hoaDon.get());

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
        }

//        return "/view/BanHangTaiQuay/ban-hang-tai-quay.jsp";
        return "/view/BanHangTaiQuay/index2.jsp";
    }
    //Lọc kiểu tay
    @GetMapping("locSPCTByKieuTay/{idKieuTay}")
    public String locSPCTByKieuTay(
            Model model,
            @PathVariable("idKieuTay") String idKieuTay,
            @RequestParam("page") Optional<Integer> pageParam
    ){

        if (idHoaDon.equals("")){
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
        }else {
            Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHoaDon);
            model.addAttribute("hoaDon",hoaDon.get());

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
        }

        return "/view/BanHangTaiQuay/index2.jsp";
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
        return "/view/BanHangTaiQuay/banHangTaiQuay.jsp";
    }

    //Find khách hàng
    @PostMapping("/find-kh/{idKH}")
    public String findKhachHangID(@PathVariable String idKH,
                                  @RequestParam String idHoaDon,
                                  @RequestParam String maHoaDon,
                                  @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDateTime ngayTao){


        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(idHoaDon);

        String ma1="HD";
        Integer sum = hoaDonRepository.countHD() + 1;
        //test

        String ma = ma1 + sum;
        System.out.println("==============test hoa don:"+ma);

        KhachHang khachHang = new KhachHang();
        khachHang.setId(idKH);
        hoaDon.setIdKhachHang(khachHang);
        //Tạo mã tự sinh
        hoaDon.setNgayTao(ngayTao);
        Optional<NhanVien> nv = nhanVienRepo.findById(idNV);
        hoaDon.setIdNhanVien(nv.get());

        hoaDon.setMa(maHoaDon);
        hoaDon.setTrangThai(0);

        hoaDonRepository.save(hoaDon);

        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
    }


    // Find khuyến mãi
    @PostMapping("/find-khuyen-mai/{idKM}")
    public String findKhuyenMai(@PathVariable String idKM,
                                @RequestParam String idHoaDon){

        for (HoaDon hoaDon:listHoaDon){
            if (hoaDon.getId().equals(idHoaDon)){
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setId(idKM);
                hoaDon.setIdKhuyenMai(khuyenMai);
                hoaDonRepository.save(hoaDon);
            }
        }

        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
    }


    //Search khách hàng
    @PostMapping("search/{idHD}")
    public String search(@PathVariable String idHD,
                         @RequestParam("txt") String txt,
                         @RequestParam("page") Optional<Integer> pageParam,
                         Model model
    ){


        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        model.addAttribute("hoaDon",hoaDon.get());

        int page = pageParam.orElse(0);
        Pageable p = PageRequest.of(page,100);
        Page<KhachHang> pageData = khachHangRepository.timKiemKhachHang(txt,khachHangRepository.ACTIVE,p);
        model.addAttribute("pageData",pageData);
        int total = pageData.getTotalPages();
        model.addAttribute("total",total);


        return "/view/BanHangTaiQuay/hienThi.jsp";
    }

    //Thêm nhanh khách hàng
    @PostMapping("create")
    public String createKhachHang(@RequestParam String idHoaDon,
                                  @RequestParam String hoTen,
                                  @RequestParam String sdt){

        KhachHang khachHang = new KhachHang();
        khachHang.setHoTen(hoTen);
        khachHang.setSdt(sdt);
        khachHang.setTaiKhoan(hoTen);
        khachHang.setMatKhau("12345");
        khachHang.setTrangThai(1);
        khachHangRepository.save(khachHang);
        return "redirect:/ban-hang-tai-quay/detail-hoa-don/" + idHoaDon;
    }

    @CrossOrigin
    @GetMapping("api/lst-spct")
    public ResponseEntity<List<ChiTietSanPham>> getLstSPCT(@RequestParam(value = "page",defaultValue ="0") String pageParam){
        Pageable pageable = PageRequest.of(Integer.valueOf(pageParam), 10);
        return ResponseEntity.ok(sanPhamChiTietRepository.findByTrangThai(1,pageable).getContent());
    }

    //Cập nhật số lượng
    @CrossOrigin
    @PostMapping("/api-update-sl/{idCTSP}")
    public ResponseEntity<String> updateSoLuong2(@PathVariable String idCTSP,
                                                 @RequestBody Map<String, Integer> request,
                                                 Model model){

        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(idCTSP);
        int newQuantity = request.get("quantity");

//        int tongSl = chiTietSanPham.getSoLuong();
//        model.addAttribute("tongSl",tongSl);
        for(ChiTietHoaDon chiTietHoaDon : listHDCT){
            if (chiTietHoaDon.getIdCTSP().getId().equals(idCTSP) && chiTietHoaDon.getIdHoaDon().getId().equals(idHoaDon)) {

                int sl = chiTietHoaDon.getSoLuong();
                chiTietHoaDon.setSoLuong(newQuantity);

                hoaDonChiTietRepository.save(chiTietHoaDon);

                //Số lượng của sản phẩm chi tiết -1 khi ấn vào button thêm trong giỏ hàng
                if (chiTietSanPham.getId().equals(idCTSP) ) {
                    chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - chiTietHoaDon.getSoLuong() + sl);
                    sanPhamChiTietRepository.save(chiTietSanPham);
                }
            }

        }

        return ResponseEntity.ok("ok");
    }

    @CrossOrigin
    @GetMapping("api/get-spct/{idSPCT}")
    public ResponseEntity<ChiTietSanPham> getSPCT(@PathVariable("idSPCT") String idSPCT){
        return ResponseEntity.ok(sanPhamChiTietRepository.findById(idSPCT).get());
    }


    @CrossOrigin
    @PostMapping("api/add-hdct/{idHoaDon}/{idSPCT}/{donGia}")
    public ResponseEntity<String> addLstHDCT(@PathVariable("idHoaDon") String idHoaDon,@PathVariable("idSPCT") String idSPCT,@PathVariable("donGia") String donGia ){
//        System.out.println("test post mapping ajax+++++++++++++++++++++++++++++++++++++++++: ");
//        ChiTietHoaDon cthd = new ChiTietHoaDon();
//        BigDecimal big = new BigDecimal(donGia);
//        cthd.setIdHoaDon(hoaDonRepository.findById(idHoaDon).get());
//        cthd.setIdCTSP(sanPhamChiTietRepository.findByIdCTSP(idSPCT));
//        cthd.setDonGia(big);
//        cthd.setSoLuong(1);
//        cthd.setTrangThai(0);
//        hoaDonChiTietRepository.save(cthd);
//        return ResponseEntity.ok(true);

//        ChiTietHoaDon hdct = new ChiTietHoaDon();
//        //Tìm sản phẩm trong giỏ hàng
//        boolean spTonTaiTrongGioHang = false;
//        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(idSPCT);
////        ChiTietHoaDon  = hoaDonChiTietRepository.findHDCTByIdHoaDon(idHoaDon);
//        for (ChiTietHoaDon chiTietHoaDon : listHDCT) {
//            //Nếu số lượng trong spct = 0 thì không đưuọc thêm sản phẩm nữa
//            if (chiTietSanPham.getSoLuong() <= 0) {
//                chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());
//                try {
//                    hoaDonChiTietRepository.save(chiTietHoaDon);
//                    spTonTaiTrongGioHang = true;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//            else {
//                if (chiTietHoaDon.getIdCTSP().getId().equals(idSPCT) && chiTietHoaDon.getIdHoaDon().getId().equals(idHoaDon)) {
//                    //Thêm số lượng sản phẩm +1 khi ấn vào button thêm trong giỏ hàng
//                    chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong() + 1);
//                    hoaDonChiTietRepository.save(chiTietHoaDon);
//
//                    //Số lượng của sản phẩm chi tiết bị -1 khi ấn vào button thêm trong giỏ hàng
//                    if (chiTietSanPham.getId().equals(idSPCT)) {
//                        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
//                        sanPhamChiTietRepository.save(chiTietSanPham);
//                    }
//                    spTonTaiTrongGioHang = true;
//                    break;
//                }
//            }
//        }
//
//        if (!spTonTaiTrongGioHang) {
//
//            BigDecimal big = new BigDecimal(donGia);
//            ChiTietSanPham ctsp = new ChiTietSanPham();
//            ctsp.setId(idSPCT);
//            hdct.setIdCTSP(ctsp);
//            HoaDon hoaDon = new HoaDon();
//            hoaDon.setId(idHoaDon);
//            hdct.setIdHoaDon(hoaDon);
//            hdct.setSoLuong(1);
//            hdct.setDonGia(big);
//            hoaDonChiTietRepository.save(hdct);
//            //Số lượng của sản phẩm chi tiết bị giảm 1 khi ấn vào button thêm trong giỏ hàng
//            if (chiTietSanPham.getId().equals(idSPCT)) {
//                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
//                sanPhamChiTietRepository.save(chiTietSanPham);
//            }
//        }

        ChiTietSanPham chiTietSanPham = sanPhamChiTietRepository.findByIdCTSP(idSPCT);
        boolean flag = false;
        String idHDCT = "";
        //Kiểm tra xem sản phẩm đã có hay chưa
        for (ChiTietHoaDon cthd : listHDCT) {
            if (chiTietSanPham.getId().equals(cthd.getIdCTSP().getId())) {
                idHDCT = cthd.getId();
                flag = true;
                break;
            }
        }
        if (flag){
            ChiTietHoaDon cthd = hoaDonChiTietRepository.findById(idHDCT).get();
            cthd.setSoLuong(cthd.getSoLuong() + 1);
            cthd.setDonGia(cthd.getDonGia());
            hoaDonChiTietRepository.save(cthd);
        }else {
            ChiTietHoaDon hdct = new ChiTietHoaDon();
            BigDecimal big = new BigDecimal(donGia);
//            ChiTietSanPham ctsp = new ChiTietSanPham();
//            ctsp.setId(idSPCT);
            hdct.setIdCTSP(chiTietSanPham);
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(idHoaDon);
            hdct.setIdHoaDon(hoaDon);
            hdct.setSoLuong(1);
            hdct.setDonGia(big);
            hoaDonChiTietRepository.save(hdct);
        }

        return ResponseEntity.ok("ok");
    }

    @CrossOrigin
    @GetMapping("api/lst-hdct/{idHoaDon}")
    public ResponseEntity<List<ChiTietHoaDon>> getLstHDCT(@PathVariable("idHoaDon") String idHoaDon){
//        return ResponseEntity.ok(hoaDonChiTietRepository.findDistinctByHoaDon_Id(idHoaDon));
        return ResponseEntity.ok(hoaDonChiTietRepository.findAllByHoaDon_Id(idHoaDon));
    }


    //Test
    @CrossOrigin
    @GetMapping("api/get-hd/{idHoaDon}")
    public ResponseEntity<HoaDon> getHoaDon(@PathVariable("idHoaDon") String idHoaDon){
        return ResponseEntity.ok(hoaDonRepository.findById(idHoaDon).get());
    }

}
