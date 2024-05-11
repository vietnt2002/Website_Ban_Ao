package com.example.java4.repositories;
import com.example.java4.entities.SanPhamChiTiet;
import com.example.java4.response.SanPhamChiTietResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface SPCTRepository extends JpaRepository<SanPhamChiTiet,Integer>{

    @Query("select new com.example.java4.response.SanPhamChiTietResponse(spct.id, ms.tenMauSac, kt.tenKichThuoc, sp.tenSanPham, spct.maSPCT, spct.soLuong, spct.donGia, spct.trangThai)" +
            "            from SanPhamChiTiet spct join MauSac ms on spct.id = ms.id" +
            "            join KichThuoc kt on spct.id = kt.id \n" +
            "            join SanPham sp on spct.id = sp.id")
    List<SanPhamChiTietResponse> getList();

    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<SanPhamChiTiet> findByTrangThai(int trangThai, Pageable pageable);
};
