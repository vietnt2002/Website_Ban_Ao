package com.example.java4.repositories;

import com.example.java4.entities.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi,String> {
    public static final int MAC_DINH  = 1;
    public static final int TUY_CHON = 0;

    @Query("select dc from DiaChi dc where dc.idKhachHang.id = ?1 and dc.trangThai = ?2")
    DiaChi getDiaChiByIdKhachHangAndTrangThai(String idKH, Integer trangThai);

    @Query("select dc from DiaChi dc order by dc.trangThai desc")
    List<DiaChi> getAllDiaChi();

    @Query("select dc from DiaChi dc where dc.id = ?1")
    DiaChi findDiaChiByID(String id);
}
