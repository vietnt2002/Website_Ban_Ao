package com.example.java4.controller.QLKH;

import com.example.java4.config.UserInfor;
import com.example.java4.entities.ChucVu;
import com.example.java4.entities.DiaChi;
import com.example.java4.entities.KhachHang;
import com.example.java4.entities.NhanVien;
import com.example.java4.repositories.ChucVuRepository;
import com.example.java4.repositories.DiaChiRepository;
import com.example.java4.repositories.KhachHangRepository;
import com.example.java4.repositories.NhanVienRepository;
import com.example.java4.request.req_viet.ChucVuRequest;
import com.example.java4.request.req_viet.KhachHangRequest;
import com.example.java4.request.req_viet.NhanVienRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("qlkh")
public class QuanLyKhachHangController {
    @Autowired
    KhachHangRepository khachHangRepo;

    @Autowired
    NhanVienRepository nhanVienRepo;

    @Autowired
    DiaChiRepository diaChiRepo;

    @Value("src/main/webapp/imageUser")
    private String fileUpload;

    //Quản lý khách hàng
    @GetMapping("quan-ly-khach-hang")
    public String getQuanLyKhachHang(
            Model model,
            @RequestParam("page") Optional<Integer> pageParam
    ) {
        //Hiển thị thông tin nhân viên đăng nhập
        if (UserInfor.idNhanVien != null) {
            NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNhanVien).get();
            model.addAttribute("nv", nhanVien);
        }

        Pageable pageable = PageRequest.of(pageParam.orElse(0), 5);
        Page<NhanVien> pageNhanVien = nhanVienRepo.getAlll(pageable);
        List<KhachHang> listKhachHang = khachHangRepo.findAll();
//        List<DiaChi> listChucVuByTrangThai = diaChiRepo.findBy(ChucVuRepository.ACTIVE);
//        model.addAttribute("listChucVu", listChucVuByTrangThai);
        model.addAttribute("listKhachHang", listKhachHang);
        return "/view/QLKH/quanLyKhachHang.jsp";
    }

    @GetMapping("/them-khach-hang-view")
    public String themKhachHangView(
            Model model
    ) {
        //Hiển thị thông tin nhân viên đăng nhập
        if (UserInfor.idNhanVien != null) {
            NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNhanVien).get();
            model.addAttribute("nv", nhanVien);
        }
        KhachHangRequest khachHangRequest = new KhachHangRequest();
        model.addAttribute("listKhachHang", khachHangRepo.findAll());
        model.addAttribute("khachHang", khachHangRequest);
        return "/view/QLKH/themKhachHang.jsp";
    }

    @PostMapping("them-khach-hang")
    public String themKhachHang(
            @Valid @ModelAttribute("khachHang") KhachHangRequest khReq
    ) {
        KhachHang khachHang = new KhachHang();
        khachHang.setHoTen(khReq.getHoTen());
        khachHang.setSdt(khReq.getSdt());
        khachHang.setEmail(khReq.getEmail());
        khachHang.setNgaySinh(khReq.getNgaySinh());
        khachHang.setGioiTinh(khReq.getGioiTinh());

        // Lấy ra tên ảnh
        String imgName = khReq.getAnhDaiDien().getOriginalFilename();
        try {
            //Lưu ảnh vào file chung
            if (!khReq.getAnhDaiDien().isEmpty()) {
                byte[] bytes = khReq.getAnhDaiDien().getBytes();
                Path path = Paths.get(fileUpload + File.separator + imgName);
                Files.write(path, bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        khachHang.setAnhDaiDien(imgName);

        khachHang.setNgayTao(LocalDateTime.now());
        khachHang.setTrangThai(NhanVienRepository.ACTIVE);
        khachHangRepo.save(khachHang);
        return "redirect:/qlkh/quan-ly-khach-hang";
    }



    @GetMapping("/sua-khach-hang-view/{id}")
    public String suaNhanVienView(
            Model model,
            @PathVariable("id") KhachHang khachHang
    ) {
        //Hiển thị thông tin nhân viên đăng nhập
        if (UserInfor.idNhanVien != null) {
            NhanVien nhanVienTK = nhanVienRepo.findById(UserInfor.idNhanVien).get();
            model.addAttribute("nv", nhanVienTK);
        }
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("listKhachHang", khachHangRepo.findAll());
        return "/view/QLKH/suaKhachHang.jsp";
    }

    @PostMapping("sua-khach-hang/{id}")
    public String suaNhanVien(
            @PathVariable("id") KhachHang khachHang,
            @RequestParam("img") String anhDaiDien,
            @Valid @ModelAttribute("khachHang") KhachHangRequest khReq
    ) {
        khachHang.setId(khachHang.getId());
        khachHang.setHoTen(khReq.getHoTen());
        khachHang.setSdt(khReq.getSdt());
        khachHang.setEmail(khReq.getEmail());
        khachHang.setNgaySinh(khReq.getNgaySinh());
        khachHang.setGioiTinh(khReq.getGioiTinh());

        // Lấy ra tên ảnh
        if (khReq.getAnhDaiDien() == null || khReq.getAnhDaiDien().isEmpty()){
            khachHang.setAnhDaiDien(anhDaiDien);
        }else {
            // Lấy ra tên ảnh
            String imgName = khReq.getAnhDaiDien().getOriginalFilename();
            try {
                //Lưu ảnh vào file chung
                byte[] bytes = khReq.getAnhDaiDien().getBytes();
                Path path = Paths.get(fileUpload + File.separator + imgName);
                Files.write(path, bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            khachHang.setAnhDaiDien(imgName);
        }

        khachHang.setNgaySua(LocalDateTime.now());
        khachHang.setTrangThai(khReq.getTrangThai());
        khachHangRepo.save(khachHang);
        return "redirect:/qlkh/quan-ly-khach-hang";
    }

    @PostMapping("tim-kiem")
    public String timKiem(
            Model model,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "idCV", required = false) String idCV
    ) {
        //Hiển thị thông tin nhân viên đăng nhập
//        if (UserInfor.idNhanVien != null) {
//            NhanVien nhanVien = nhanVienRepo.findById(UserInfor.idNhanVien).get();
//            model.addAttribute("nv", nhanVien);
//        }
//
//        List<NhanVien> listNhanVien = nhanVienRepo.findAll();
//
//        if ((key != null || !key.isEmpty()) && idCV.isEmpty()) {
//            listNhanVien = nhanVienRepo.findByHoTenOrSdt(key);
//        } else if (key.isEmpty()) {
//            listNhanVien = nhanVienRepo.findByChucVu(idCV);
//        } else if (!key.isEmpty() && !idCV.isEmpty()) {
//            listNhanVien = nhanVienRepo.findByHoTenOrSdtAndChucVu(key, idCV);
//        }
//
//        List<ChucVu> listChucVuByTrangThai = chucVuRepo.findByTrangThai(ChucVuRepository.ACTIVE);
//        model.addAttribute("listChucVu", listChucVuByTrangThai);
//        model.addAttribute("listNhanVien", listNhanVien);
        return "/view/QLNV/quanLyNhanVien.jsp";
    }


    @GetMapping("/tai-khoan-cua-toi/{id}")
    public String taiKhoanDangNhap(
            Model model,
            @PathVariable("id") NhanVien nhanVien
    ) {
        //Hiển thị thông tin nhân viên đăng nhập
//        if (UserInfor.idNhanVien != null) {
//            NhanVien nhanVienTK = nhanVienRepo.findById(UserInfor.idNhanVien).get();
//            model.addAttribute("nv", nhanVienTK);
//        }
//        List<ChucVu> listChucVu = chucVuRepo.findByTrangThai(ChucVuRepository.ACTIVE);
//        model.addAttribute("nhanVien", nhanVien);
//        model.addAttribute("listChucVu", listChucVu);
//        model.addAttribute("listNhanVien", nhanVienRepo.findAll());
        return "/view/QLNV/taiKhoanDangNhap.jsp";
    }

    @PostMapping("sua-tai-khoan-cua-toi/{id}")
    public String suaTaiKhoanDangNhap(
            @PathVariable("id") NhanVien nhanVien,
            @RequestParam("img") String anhDaiDien,
            @Valid @ModelAttribute("nhanVien") NhanVienRequest nvReq
    ) {

//        ChucVu chucVu = chucVuRepo.findById(nvReq.getIdCV()).get();
//        nhanVien.setId(nhanVien.getId());
//        nhanVien.setHoTen(nvReq.getHoTen());
//        nhanVien.setSdt(nvReq.getSdt());
//        nhanVien.setNgaySinh(nvReq.getNgaySinh());
//        nhanVien.setGioiTinh(nvReq.getGioiTinh());
//        nhanVien.setTaiKhoan(nvReq.getTaiKhoan());
//        nhanVien.setMatKhau(nvReq.getMatKhau());
//        nhanVien.setIdCV(chucVu);
//
//        if (nvReq.getAnhDaiDien() == null || nvReq.getAnhDaiDien().isEmpty()){
//            nhanVien.setAnhDaiDien(anhDaiDien);
//        }else {
//            // Lấy ra tên ảnh
//            String imgName = nvReq.getAnhDaiDien().getOriginalFilename();
//            try {
//                //Lưu ảnh vào file chung
//                byte[] bytes = nvReq.getAnhDaiDien().getBytes();
//                Path path = Paths.get(fileUpload + File.separator + imgName);
//                Files.write(path, bytes);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            nhanVien.setAnhDaiDien(imgName);
//        }
//
//        nhanVien.setNgaySua(LocalDateTime.now());
//        nhanVien.setTrangThai(nvReq.getTrangThai());
//        nhanVienRepo.save(nhanVien);
        return "redirect:/qlnv/quan-ly-nhan-vien";
    }
}
