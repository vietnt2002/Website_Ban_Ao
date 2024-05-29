package com.example.java4.controller.controller_tai;

import com.example.java4.repositories.HDCTRepository;
import com.example.java4.repositories.SPCTRepository;
import com.example.java4.repositories.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    //    private static HDCTRepository hdctRepository;
    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    HDCTRepository hdctRepository;

    @Autowired
    SPCTRepository spctRepository;


    int count;
    double amount;
    double total = 0;


//    @GetMapping("/view")
//    public String view(Model model){
//
//        Pageable pageable = PageRequest.of(0,3);
//        List<SanPham> listSP = sanPhamRepository.getDistinctTopBy3(pageable);
//        model.addAttribute("listSP",listSP);
//        return "cart";
//    }


    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model) {

        List<HDCT> listHDCT = hdctRepository.findAllByHoaDon_Id(4);
        for (HDCT hdct : listHDCT) {
            count = count + hdct.getSoLuong();
            amount = hdct.getSoLuong() * hdct.getDonGia();
            total += amount;
        }
        System.out.println(count);
        System.out.println(total);

        model.addAttribute("listHDCT", listHDCT);
        // Tính tổng số lượng các mặt hàng trong giỏ hàng
        model.addAttribute("count", count);
        // Tính tổng số tiền (thành tiền) các mặt hàng trong giỏ hàng
        model.addAttribute("total", total);
        return "cart";
    }


    //    Chức năng xóa sản phẩm khỏi giỏ hàng
    @GetMapping("/remove-from-cart/{idSPCT}")
    public String deleteProductToCart(@PathVariable("idSPCT") int idSPCT) {

//

        // Lấy ra thông tin hóa đơn hiện tại của người dùng
//        HoaDon hoaDon = this.hoaDonRepository.findById(userInfo.idHoaDon).orElse(null);
        // Lấy ra thông tin chi tiết sản phẩm để xóa
        SPCT sanPhamChiTiet = this.spctRepository.findById(idSPCT).orElse(null);

        // Tìm và xóa chi tiết hóa đơn chứa sản phẩm cần xóa
        HDCT hoaDonChiTiet = this.hdctRepository.findByHoaDonAndIdSanPhamChiTiet(2, sanPhamChiTiet );

        if (hoaDonChiTiet != null) {
//            int soLuong = hoaDonChiTiet.getSoLuong(); // Số lượng sản phẩm sẽ được cập nhật lại trong hóa đơn
//            // Cập nhật lại số lượng sản phẩm trong bảng sản phẩm
//            int soLuongHienTai = sanPhamChiTiet.getSoLuong();
//            sanPhamChiTiet.setSoLuong(soLuongHienTai + soLuong);// Trả lại số lượng vào chi tiết sản phẩm trong kho
            this.spctRepository.save(sanPhamChiTiet);
            this.hdctRepository.delete(hoaDonChiTiet);
        }

        return "redirect:/cart/shoppingCart";
    }


}