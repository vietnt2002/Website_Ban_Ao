package com.example.java4.repositories;

import com.example.java4.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository
        extends JpaRepository<HoaDon,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;

    public static final int HOA_DON_ONL = 0;
    public static final int HOA_DON_OFF = 1;

    public static final int CHO_THANH_TOAN = 0;
    public static final int CHO_XAC_NHAN = 1;
    public static final int DA_XAC_NHAN = 2;
    public static final int CHO_GIAO_HANG = 3;
    public static final int DANG_GIAO_HANG = 4;
    public static final int GIAO_HANG_THANH_CONG = 5;
    public static final int DA_HOAN_THANH = 6;
    public static final int DA_HUY= 7;
    @Query(value = "SELECT TOP 5 * FROM HoaDon where trangThai = 0 ORDER BY ngayTao DESC",
            nativeQuery = true)
    public List<HoaDon>  selectTop5();
    public Optional<HoaDon> findById(String id);
    @Query(value = "SELECT COUNT(*) FROM dbo.HoaDon",nativeQuery = true)
    Integer countHD();
    public Page<HoaDon> findByTrangThai(int trangThai, Pageable pageable);
    // Lấy ra Page danh sách hóa đơn theo trạng thái được sắp xếp theo ngày giờ mới nhất
    Page<HoaDon> findByTrangThaiOrderByNgayTaoDesc(int trangThai, Pageable pageable);
    //Lấy ra hóa đơn theo idKH, loại hóa đơn và trạng thái
    @Query("select hd from HoaDon hd where hd.idKhachHang.id = ?1 and hd.loaiHoaDon = ?2 and hd.trangThai = ?3")
    HoaDon findByidKHAndLoaiHoaDonAndTrangThai(String idKH, Integer loaiHD, Integer trangThai);
};
