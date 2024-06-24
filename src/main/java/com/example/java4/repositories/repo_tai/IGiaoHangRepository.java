package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.GiaoHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IGiaoHangRepository extends JpaRepository<GiaoHang,String> {

    @Query("SELECT gh FROM GiaoHang gh WHERE gh.idHoaDon.id = :idHoaDon")
    GiaoHang findByHoaDonId(@Param("idHoaDon") String idHoaDon);


}
