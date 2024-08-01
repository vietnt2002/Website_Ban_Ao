package com.example.java4.repositories;

import com.example.java4.entities.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SanPhamRepository
        extends JpaRepository<SanPham,String>
{
    int ACTIVE  = 1;
    int INACTIVE =0;
    @Query(value = "SELECT sp FROM SanPham sp ORDER BY sp.ngayTao ASC")
    Page<SanPham> findByTrangThai(int trangThai, Pageable pageable);
    @Query(value = "SELECT sp FROM SanPham sp ORDER BY sp.ngayTao ASC")
    Page<SanPham> findAllByPage(Pageable pageable);
    @Query("UPDATE SanPham sp SET sp.trangThai = 1 WHERE sp.id=:id")
    int enableStt(@Param("id") String id);
    @Query("UPDATE SanPham sp SET sp.trangThai = 0 WHERE sp.id=:id")
    int disableStt(@Param("id")String id);
    @Query(value = "SELECT COUNT(*) FROM sanpham",nativeQuery = true)
    Integer getCount();
    @Query(value = "SELECT COUNT(*) FROM sanpham where trangThai=1",nativeQuery = true)
    Integer getCountStt1();
    @Query(value = "SELECT COUNT(*) FROM sanpham where trangThai=0",nativeQuery = true)
    Integer getCountStt0();
    @Query(value = "SELECT MIN(ha.hinhAnh1) FROM HinhAnh ha WHERE ha.idCTSP.id = " +
            "(SELECT MIN(ctsp.id) FROM ChiTietSanPham ctsp WHERE ctsp.idSanPham.id = :idSP)")
    String getHinhAnhOfSP(@Param("idSP") String idSP);
};
