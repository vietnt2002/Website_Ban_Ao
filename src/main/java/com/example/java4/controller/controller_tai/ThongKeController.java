package com.example.java4.controller.controller_tai;

import com.example.java4.config.UserInfor;
import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.entities.HinhAnh;
import com.example.java4.entities.NhanVien;
import com.example.java4.repositories.HinhAnhRepository;
import com.example.java4.repositories.NhanVienRepository;
import com.example.java4.repositories.SPCTRepository;
import com.example.java4.repositories.ThongKeRepository;
import com.example.java4.response.SPCTDTO;
import com.example.java4.response.ThongKeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/thong-ke")
public class ThongKeController {

    private static final Logger logger = LoggerFactory.getLogger(ThongKeController.class);

    @Autowired
    ThongKeRepository _thongKeRepo;

    @Autowired
    NhanVienRepository _nhanVienRepo;

    @Autowired
    SPCTRepository _chiTietSanPhamRepo;

    @Autowired
    HinhAnhRepository _hinhAnhRepo;


    @GetMapping("/view")
    public String view(Model model,
                       @RequestParam(value = "page", defaultValue = "0") String pageParam) {

        if (UserInfor.idNhanVien != null) {
            NhanVien nhanVien = _nhanVienRepo.findById(UserInfor.idNhanVien).get();
            model.addAttribute("nv", nhanVien);
        } else {
            return "redirect:/admin/dang-nhap-view";
        }

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate startOfMonth = today.withDayOfMonth(1);
        LocalDate startOfYear = today.withDayOfYear(1);

        LocalDate endOfWeek = startOfWeek.plusWeeks(1);
        LocalDate endOfMonth = today.plusMonths(1).withDayOfMonth(1).minusDays(1);
        LocalDate endOfYear = today.plusYears(1).withDayOfYear(1).minusDays(1);

        List<Object[]> dailyResults = _thongKeRepo.getDailyStatistics(today.atStartOfDay());
        List<Object[]> weeklyResults = _thongKeRepo.getWeeklyStatistics(startOfWeek.atStartOfDay(), endOfWeek.atStartOfDay());
        List<Object[]> monthlyResults = _thongKeRepo.getMonthlyStatistics(startOfMonth.atStartOfDay());
        List<Object[]> yearlyResults = _thongKeRepo.getYearlyStatistics(startOfYear.atStartOfDay());

        logger.info("Daily results: {}", dailyResults);
        logger.info("Weekly results: {}", weeklyResults);
        logger.info("Monthly results: {}", monthlyResults);
        logger.info("Yearly results: {}", yearlyResults);

        ThongKeDTO todayStats = ThongKeDTO.mapToThongKeDTO(dailyResults.get(0));
        ThongKeDTO weeklyStats = ThongKeDTO.mapToThongKeDTO(weeklyResults.get(0));
        ThongKeDTO monthlyStats = ThongKeDTO.mapToThongKeDTO(monthlyResults.get(0));
        ThongKeDTO yearlyStats = ThongKeDTO.mapToThongKeDTO(yearlyResults.get(0));

        model.addAttribute("today", todayStats);
        model.addAttribute("week", weeklyStats);
        model.addAttribute("month", monthlyStats);
        model.addAttribute("year", yearlyStats);


        // Hiển thị danh sách sản phẩm sắp hết hàng
        Map<String, HinhAnh> hinhAnhMapCTSP = getHinhAnhMapCTSP();
        PageRequest pageRequest = PageRequest.of(Integer.valueOf(pageParam), 5);
        Page<ChiTietSanPham> lowStockProducts = _thongKeRepo.getLowStockProducts(pageRequest);
        model.addAttribute("pageSanPhamSapHetHang", lowStockProducts);
        model.addAttribute("hinhAnhMapCTSP", hinhAnhMapCTSP);

        return "/view/ThongKe/view.jsp";
    }


    //Lấy ra hình ảnh trong chi tiết sản phẩm
    private Map<String, HinhAnh> getHinhAnhMapCTSP() {
        Map<String, HinhAnh> hinhAnhMapCTSP = new HashMap<>();
        List<ChiTietSanPham> listChiTietSanPham = _chiTietSanPhamRepo.findAll();
        for (ChiTietSanPham ctsp : listChiTietSanPham) {
            HinhAnh hinhAnh = _hinhAnhRepo.findByIdCTSP(ctsp.getId());
            if (hinhAnh != null) {
                hinhAnhMapCTSP.put(ctsp.getId(), hinhAnh);
            }
        }
        return hinhAnhMapCTSP;
    }

}
