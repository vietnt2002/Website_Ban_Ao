package com.example.java4.repositories;
import com.example.java4.entities.HDCT;
import com.example.java4.entities.SPCT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface HDCTRepository
        extends JpaRepository<HDCT,Integer>
{
        public static final int ACTIVE = 1;
        public static final int INACTIVE = 0;
        public Page<HDCT> findByTrangThai(int trangThai, Pageable pageable);
        public List<HDCT> findAllByHoaDon_Id(Integer id);
        @Query ("SELECT h FROM HDCT h WHERE h.hoaDon.id = :hoaDon AND h.spct = :sanPhamChiTiet")
        HDCT findByHoaDonAndIdSanPhamChiTiet(@PathVariable ("hoaDon") Integer hoaDon, @PathVariable ("sanPhamChiTiet") SPCT sanPhamChiTiet);
};
