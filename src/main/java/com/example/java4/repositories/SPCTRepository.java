package com.example.java4.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPCTRepository extends JpaRepository<SPCT,Integer>{

//    @Query("select new com.example.java4.response.SanPhamChiTietResponse(spct.id, ms.tenMauSac, kt.tenKichThuoc, sp.ten, spct.maSPCT, spct.soLuong, spct.donGia, spct.trangThai)" +
//            "            from SPCT spct join MauSac ms on spct.idMauSac.id = ms.id" +
//            "            join KichThuoc kt on spct.idKichThuoc.id = kt.id \n" +
//            "            join SanPham sp on spct.idSanPham.id = sp.id")
//    List<SanPhamChiTietResponse> getList();

    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<SPCT> findByTrangThai(int trangThai, Pageable pageable);
};
