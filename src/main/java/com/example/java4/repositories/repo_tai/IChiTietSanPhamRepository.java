package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham,String> {
//    @Query("SELECT ctsp FROM ChiTietSanPham ctsp " +
//            "JOIN ChiTietHoaDon hdct " +
//            "WHERE hdct.id = :idHoaDonChiTiet")
//    Optional<ChiTietSanPham> findByHoaDonChiTietId(@Param("idHoaDonChiTiet") String idHoaDonChiTiet);

    @Query(value = "SELECT ctsp.* FROM ChiTietSanPham ctsp " +
            "JOIN ChiTietHoaDon hdct ON ctsp.id = hdct.idCTSP " +
            "WHERE hdct.id = :idHoaDonChiTiet", nativeQuery = true)
    Optional<ChiTietSanPham> findByHoaDonChiTietId(@Param("idHoaDonChiTiet") String idHoaDonChiTiet);

}
