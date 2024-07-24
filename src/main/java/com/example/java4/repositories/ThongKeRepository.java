package com.example.java4.repositories;

import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.entities.HoaDon;
import com.example.java4.response.SPCTDTO;
import com.example.java4.response.ThongKeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ThongKeRepository extends JpaRepository<HoaDon,String> {

    // Doanh thu ngày hôm nay
    @Query(value = "SELECT " +
            "COALESCE(SUM(hd.TongTien), 0) AS doanhThu, " +
            "COALESCE(SUM(cthd.SoLuong), 0) AS soLuongSanPhamDaBan, " +
            "COUNT(DISTINCT CASE WHEN hd.TrangThai = 6 THEN hd.ID ELSE NULL END) AS donHangThanhCong, " +
            "COUNT(DISTINCT CASE WHEN hd.TrangThai = 7 THEN hd.ID ELSE NULL END) AS donHangDaHuy " +
            "FROM HoaDon hd " +
            "LEFT JOIN ChiTietHoaDon cthd ON cthd.IdHoaDon = hd.ID " +
            "WHERE CAST(hd.NgayTao AS DATE) = CAST(:date AS DATE)",
            nativeQuery = true)
    List<Object[]> getDailyStatistics(@Param("date") LocalDateTime date);

    // Thống kê cho tuần
    @Query(value = "SELECT " +
            "COALESCE(SUM(hd.TongTien), 0) AS doanhThu, " +
            "COALESCE(SUM(cthd.SoLuong), 0) AS soLuongSanPhamDaBan, " +
            "COUNT(DISTINCT CASE WHEN hd.TrangThai = 6 THEN hd.ID ELSE NULL END) AS donHangThanhCong, " +
            "COUNT(DISTINCT CASE WHEN hd.TrangThai = 7 THEN hd.ID ELSE NULL END) AS donHangDaHuy " +
            "FROM HoaDon hd " +
            "LEFT JOIN ChiTietHoaDon cthd ON cthd.IdHoaDon = hd.ID " +
            "WHERE hd.NgayTao BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Object[]> getWeeklyStatistics(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // Thống kê cho tháng
    @Query(value = "SELECT " +
            "COALESCE(SUM(hd.TongTien), 0) AS doanhThu, " +
            "COALESCE(SUM(cthd.SoLuong), 0) AS soLuongSanPhamDaBan, " +
            "COUNT(DISTINCT CASE WHEN hd.TrangThai = 6 THEN hd.ID ELSE NULL END) AS donHangThanhCong, " +
            "COUNT(DISTINCT CASE WHEN hd.TrangThai = 7 THEN hd.ID ELSE NULL END) AS donHangDaHuy " +
            "FROM HoaDon hd " +
            "LEFT JOIN ChiTietHoaDon cthd ON cthd.IdHoaDon = hd.ID " +
            "WHERE DATEPART(MONTH, hd.NgayTao) = DATEPART(MONTH, :date) " +
            "AND DATEPART(YEAR, hd.NgayTao) = DATEPART(YEAR, :date)",
            nativeQuery = true)
    List<Object[]> getMonthlyStatistics(@Param("date") LocalDateTime date);

    // Thống kê cho năm
    @Query(value = "SELECT " +
            "COALESCE(SUM(hd.TongTien), 0) AS doanhThu, " +
            "COALESCE(SUM(cthd.SoLuong), 0) AS soLuongSanPhamDaBan, " +
            "COUNT(DISTINCT CASE WHEN hd.TrangThai = 6 THEN hd.ID ELSE NULL END) AS donHangThanhCong, " +
            "COUNT(DISTINCT CASE WHEN hd.TrangThai = 7 THEN hd.ID ELSE NULL END) AS donHangDaHuy " +
            "FROM HoaDon hd " +
            "LEFT JOIN ChiTietHoaDon cthd ON cthd.IdHoaDon = hd.ID " +
            "WHERE DATEPART(YEAR, hd.NgayTao) = DATEPART(YEAR, :date)",
            nativeQuery = true)
    List<Object[]> getYearlyStatistics(@Param("date") LocalDateTime date);


    // Thống kê danh sách sản phẩm sắp hết hàng
    @Query(value = "SELECT ctsp.* " +
            "FROM ChiTietSanPham ctsp " +
            "JOIN SanPham sp ON ctsp.SanPhamID = sp.ID " +
            "LEFT JOIN HinhAnh ha ON ctsp.ID = ha.ChiTietSanPhamID " +
            "WHERE ctsp.soLuong <= 10 " +
            "ORDER BY ctsp.soLuong ASC",
            countQuery = "SELECT COUNT(*) " +
                    "FROM ChiTietSanPham ctsp " +
                    "JOIN SanPham sp ON ctsp.SanPhamID = sp.ID " +
                    "LEFT JOIN HinhAnh ha ON ctsp.ID = ha.ChiTietSanPhamID " +
                    "WHERE ctsp.soLuong <= 10",
            nativeQuery = true)
    Page<ChiTietSanPham> getLowStockProducts(Pageable pageable);



}
