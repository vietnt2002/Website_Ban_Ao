package com.example.java4.restcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("nhan_vien")
public class NhanVienController {
//    StoreRequest rem;
//    @Autowired
//    NhanVienRepository nvRepo;
//    public NhanVienController() {
//        this.rem = new StoreRequest();
//    }


    //    Lấy tất cả các dữ liệu danh sách nhân viên
//    @GetMapping("/get-all")
//    public List<NhanVien> index(Model model) {
//        return nvRepo.findAll();
//    }
//
//    // Lấy dữ liệu đối tượng Nhân Viên theo Id
//    @GetMapping("/detail/{id}")
//    public NhanVien detail(@PathVariable("id") Integer id, Model model) {
//        return nvRepo.findById(id).orElse(null);
//    }
//
//    //  Lấy danh sách Nhân Viên có phân trang, 5 phần tử trên 1 trang
//    @GetMapping("/phan-trang")
//    public List<NhanVien> page(@RequestParam(value = "page", defaultValue = "0") Integer pageNumber, Model model) {
//        Pageable pageable = PageRequest.of(pageNumber, 5);
//        return nvRepo.findByTrangThai(1, pageable).getContent();
//    }
//
//
//    //    Chức năng thêm mới Nhân Viên
//    @PostMapping("/create")
//    public String create(
//            @RequestBody @Valid StoreRequest nhanVienRequest,
//            BindingResult result
//    ) {
//
//        if (result.hasErrors()) {
//            System.out.println(result.getFieldError().getDefaultMessage());
//            return "Thêm mới nhân viên thất bại";
//        } else {
//            NhanVien nhanVien = new NhanVien();
//            nhanVien.setMa(nhanVienRequest.getMa());
//            nhanVien.setTen(nhanVienRequest.getTen());
//            nhanVien.setTenDangNhap(nhanVienRequest.getTenDangNhap());
//            nhanVien.setMatKhau(nhanVienRequest.getMatKhau());
//            nhanVien.setTrangThai(nhanVienRequest.getTrangThai());
//            nvRepo.save(nhanVien);
//            return "Thêm mới nhân viên thành công";
//        }
//    }
//
//
//    //    Chức năng cập nhật nhân viên
//    @PutMapping("/update-nhanvien")
//    public String doUpdate(
//            @RequestBody @Valid StoreRequest nhanVienRequest,
//            BindingResult result
//    ) {
//        if (result.hasErrors()) {
//            System.out.println(result.getFieldError().getDefaultMessage());
//            return "Sửa nhân viên thất bại";
//        } else {
//
//            NhanVien nhanVien = new NhanVien();
//            nhanVien.setId(nhanVienRequest.getId());
//            nhanVien.setMa(nhanVienRequest.getMa());
//            nhanVien.setTen(nhanVienRequest.getTen());
//            nhanVien.setTenDangNhap(nhanVienRequest.getTenDangNhap());
//            nhanVien.setMatKhau(nhanVienRequest.getMatKhau());
//            nhanVien.setTrangThai(nhanVienRequest.getTrangThai());
//            nvRepo.save(nhanVien);
//            return "Sửa nhân viên thành công";
//        }
//    }
//
//    //    Chức năng xóa màu sắc
//    @DeleteMapping("/delete-mauSac/{id}")
//    public String delete(@PathVariable(value = "id") NhanVien nhanVien) {
//
//        if (nhanVien == null) {
//            return "Xóa nhân viên thất bại";
//        }
//
//        nvRepo.delete(nhanVien);
//        return "Xóa nhân viên thành công ";
//    }

}