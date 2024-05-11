//package com.example.java4.restcontrollers;
//
//import com.example.java4.repositories.HoaDonRepository;
//import com.example.java4.repositories.KhachHangRepository;
//import com.example.java4.repositories.NhanVienRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping("hoa_don")
//public class HoaDonController {
//    ArrayList<com.example.java4.request.hoaDon.StoreRequest> ds;
//    HoaDon hdRem = new HoaDon();
//    StoreRequest rem = new StoreRequest();
//    @Autowired
//    private HoaDonRepository hdRepo;
//    @Autowired
//    private NhanVienRepository nvRepo;
//    @Autowired
//    private KhachHangRepository khRepo;
//    @Autowired
//    private HoaDonRepository hdFullRepo;
//
//    public HoaDonController() {
//        System.out.println("start new cycle");
//        this.ds = new ArrayList<>();
//    }
//
//    @GetMapping("/getList")
//    public List<HoaDon> getList() {
//        return hdRepo.findAll();
//    }
//
//    @GetMapping("/detail/{id}")
//    public HoaDon detailHoaDon(@PathVariable("id") Integer id) {
//        return hdRepo.findById(id).get();
//    }
//
//    @PostMapping("/add")
//    public String addHoaDon(@Valid @RequestBody StoreRequest request, BindingResult result) {
//        if (result.hasErrors()) {
//            System.out.println(result.getFieldError().getDefaultMessage());
//            return "Thêm thất bại";
//        } else {
//            HoaDon hoaDon = new HoaDon();
//            hoaDon.setIdKH(request.getIdKH());
//            hoaDon.setIdNV(request.getIdNV());
//            hoaDon.setNgayMuaHang(request.getNgayMuaHang());
//            hoaDon.setTrangThai(request.getTrangThai());
//            hdRepo.save(hoaDon);
//            return "Thêm thành công";
//        }
//    }
//
//    @PutMapping("/update")
//    public String updateHoaDon(@Valid @RequestBody StoreRequest request, BindingResult result) {
//        if (result.hasErrors()) {
//            System.out.println(result.getFieldError().getDefaultMessage());
//            return "Sửa thất bại";
//        } else {
//            HoaDon hoaDon = new HoaDon();
//            hoaDon.setId(request.getId());
//            hoaDon.setIdKH(request.getIdKH());
//            hoaDon.setIdNV(request.getIdNV());
//            hoaDon.setNgayMuaHang(request.getNgayMuaHang());
//            hoaDon.setTrangThai(request.getTrangThai());
//            hdRepo.save(hoaDon);
//            return "Sửa thành công";
//        }
//    }
//
//    @DeleteMapping("delete/{id}")
//    public String delete(@PathVariable("id") Integer id) {
//        hdRepo.deleteById(id);
//        return "Xóa thành công";
//    }
//}