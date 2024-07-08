package com.example.java4.repositories;

import com.example.java4.entities.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai,String> {


    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;

    @Query("select km from KhuyenMai km where km.trangThai=:trangThai")
    List<KhuyenMai> findAllKMTrangThai(@Param("trangThai") int trangThai);

    @Query("select km from KhuyenMai km where km.id=?1")
    KhuyenMai findByIdKM(String idKM);


}
