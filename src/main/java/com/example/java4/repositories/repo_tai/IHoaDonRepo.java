//package com.example.java4.repositories.repo_tai;
//
//import com.example.java4.entities.HoaDon;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//public interface IHoaDonRepo  extends JpaRepository<com.example.java4.entities.tai.HoaDon,String> {
//
//    // Loại hóa đơn
//    public static final int HOA_DON_ONL = 0;
//    public static final int HOA_DON_OFF = 1;
//
//    // Các trạng thái của hóa đơn
//    public static final int CHO_THANH_TOAN = 0;
//    public static final int CHO_XAC_NHAN = 1;
//    public static final int DA_XAC_NHAN = 2;
//    public static final int CHO_GIAO_HANG = 3;
//    public static final int DANG_GIAO_HANG = 4;
//    public static final int GIAO_HANG_THANH_CONG = 5;
//    public static final int DA_HOAN_THANH = 6;
//    public static final int DA_HUY= 7;
//
//    //Phương thức thanh toán
//    public static final int TIEN_MAT = 0;
//    public static final int CHUYEN_KHOAN = 1;
//
//    @Query(value = "SELECT TOP 5 * FROM HoaDon where trangThai = 0 ORDER BY ngayTao DESC",
//            nativeQuery = true)
//    public List<HoaDon> selectTop5();
//
//    public Optional<com.example.java4.entities.tai.HoaDon> findById(String id);
//
//    @Query(value = "SELECT COUNT(*) FROM dbo.HoaDon", nativeQuery = true)
//    Integer countHD();
//
//    public Page<HoaDon> findByTrangThai(int trangThai, Pageable pageable);
//
//
//    // Lấy ra Page danh sách hóa đơn theo trạng thái được sắp xếp theo ngày giờ mới nhất
//    Page<HoaDon> findByTrangThaiOrderByNgayTaoDesc(int trangThai, Pageable pageable);
//
//    // Tìm hóa đơn theo loại hóa đơn, chức năng lọc hóa đơn theo LoaiHoaDon
//    Page<HoaDon> findByLoaiHoaDon(int loaiHoaDon, Pageable pageable);
//
//    // Tìm hóa đơn theo loại hóa đơn, chức năng lọc hóa đơn theo LoaiHoaDon và Trạng thái của hóa đơn
//    Page<HoaDon> findByTrangThaiAndLoaiHoaDon(Integer trangThai, Integer loaiHoaDon, Pageable pageable);
//
//
//    @Query("SELECT h FROM HoaDon h " +
//            "JOIN h.idKhachHang kh " +
//            "WHERE LOWER(h.ma) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//            "OR LOWER(kh.sdt) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    Page<HoaDon> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
//
//    Page<HoaDon> findByTrangThaiAndLoaiHoaDonOrderByNgayTaoDesc(int trangThai, int loaiHoaDon, Pageable pageable);
//
////    Page<HoaDon> findByNgayTaoBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
//
//    @Query("SELECT h FROM HoaDon h " +
//            "JOIN h.idKhachHang kh " +
//            "WHERE (LOWER(h.ma) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//            "OR LOWER(kh.sdt) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
//            "AND (:startDate IS NULL OR h.ngayTao >= :startDate) " +
//            "AND (:endDate IS NULL OR h.ngayTao <= :endDate)")
//    Page<HoaDon> searchByKeywordAndDate(@Param("keyword") String keyword,
//                                        @Param("startDate") LocalDateTime startDate,
//                                        @Param("endDate") LocalDateTime endDate,
//                                        Pageable pageable);
//
//
//}
