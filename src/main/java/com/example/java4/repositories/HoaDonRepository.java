package com.example.java4.repositories;

import com.example.java4.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository
        extends JpaRepository<HoaDon,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;


    @Query(value = "SELECT TOP 5 * FROM HoaDon where trangThai = 0 ORDER BY ngayTao DESC",
            nativeQuery = true)
    public List<HoaDon>  selectTop5();
    public Optional<HoaDon> findById(String id);
    @Query(value = "SELECT COUNT(*) FROM dbo.HoaDon",nativeQuery = true)
    Integer countHD();

    public Page<HoaDon> findByTrangThai(int trangThai, Pageable pageable);


    //// Lấy ra Page danh sách hóa đơn theo trạng thái được sắp xếp theo ngày giờ mới nhất
    Page<HoaDon> findByTrangThaiOrderByNgayTaoDesc(int trangThai, Pageable pageable);




};
