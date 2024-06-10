package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IHoaDonRepository   extends JpaRepository<HoaDon,String> {

    // Loại hóa đơn
    public static final int HOA_DON_ONL = 0;
    public static final int HOA_DON_OFF = 1;

    // Các trạng thái của hóa đơn
    public static final int CHO_THANH_TOAN = 0;
    public static final int CHO_XAC_NHAN = 1;
    public static final int DA_XAC_NHAN = 2;
    public static final int CHO_GIAO_HANG = 3;
    public static final int DANG_GIAO_HANG = 4;
    public static final int GIAO_HANG_THANH_CONG = 5;
    public static final int DA_HOAN_THANH = 6;

    //Phương thức thanh toán
    public static final int TIEN_MAT = 0;
    public static final int CHUYEN_KHOAN = 1;

    @Query(value = "SELECT TOP 5 * FROM HoaDon where trangThai = 0 ORDER BY ngayTao DESC",
            nativeQuery = true)
    public List<HoaDon> selectTop5();

    public Optional<HoaDon> findById(String id);

    @Query(value = "SELECT COUNT(*) FROM dbo.HoaDon", nativeQuery = true)
    Integer countHD();

    public Page<HoaDon> findByTrangThai(int trangThai, Pageable pageable);


    // Lấy ra Page danh sách hóa đơn theo trạng thái được sắp xếp theo ngày giờ mới nhất
    Page<HoaDon> findByTrangThaiOrderByNgayTaoDesc(int trangThai, Pageable pageable);

    // Tìm hóa đơn theo loại hóa đơn, chức năng lọc hóa đơn theo LoaiHoaDon
    Page<HoaDon> findByLoaiHoaDon(int loaiHoaDon, Pageable pageable);

    //Chức năng tìm kiếm hóa đơn theo nhiều tiêu chí
//    @Query("SELECT h FROM HoaDon h " +
//            "JOIN h.idKhachHang kh " +
//            "WHERE (:keyword IS NULL OR LOWER(h.ma) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//            "OR LOWER(kh.sdt) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
//            "AND (:loaiHoaDon IS NULL OR h.loaiHoaDon = :loaiHoaDon) " +
//            "AND (:ngayTao IS NULL OR FUNCTION('DATE', h.ngayTao) = :ngayTao) " +
//            "ORDER BY h.ngayTao DESC")
//    Page<HoaDon> searchHoaDon(@Param("keyword") String keyword,
//                              @Param("loaiHoaDon") Integer loaiHoaDon,
//                              @Param("ngayTao") LocalDate ngayTao,
//                              Pageable pageable);

    @Query("SELECT h FROM HoaDon h " +
            "JOIN h.idKhachHang kh " +
            "WHERE LOWER(h.ma) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(kh.sdt) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<HoaDon> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

//    @Query("SELECT h FROM HoaDon h " +
//            "JOIN h.idKhachHang kh " +
//            "WHERE h.ma LIKE :keyword " +
//            "OR kh.sdt LIKE :keyword  ")
//    Page<HoaDon> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
