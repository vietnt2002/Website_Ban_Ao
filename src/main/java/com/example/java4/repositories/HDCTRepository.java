package com.example.java4.repositories;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface HDCTRepository
        extends JpaRepository<ChiTietHoaDon,String>
{
//        public static final int ACTIVE = 1;
//        public static final int INACTIVE = 0;
//        public Page<ChiTietHoaDon> findByTrangThai(int trangThai, Pageable pageable);
//        public List<ChiTietHoaDon> findAllByHoaDon_Id(String id);
//        @Query ("SELECT h FROM ChiTietHoaDon h WHERE h.idHoaDon.id = :hoaDon AND h.idCTSP = :sanPhamChiTiet")
//        ChiTietHoaDon findByHoaDonAndIdSanPhamChiTiet(@PathVariable ("hoaDon") String hoaDon, @PathVariable ("sanPhamChiTiet") ChiTietSanPham sanPhamChiTiet);
//
};
