package com.example.java4.repositories;

import com.example.java4.entities.GiaoHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaoHangRepo extends JpaRepository<GiaoHang, String> {
    public static final int MAC_DINH  = 1;
    public static final int TUY_CHON =0;
    @Query("SELECT gh FROM GiaoHang gh WHERE gh.idHoaDon.id = :idHoaDon")
    GiaoHang findByHoaDonId(@Param("idHoaDon") String idHoaDon);

    @Query("select gh from GiaoHang gh " +
            "join HoaDon hd on hd.id = gh.idHoaDon.id " +
            "where hd.idKhachHang.id = ?1 and hd.id = ?2")
    List<GiaoHang> getListGiaoHangByIdKHAndidHD(String idKH, String idHD);
}