package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDiaChiRepository extends JpaRepository<DiaChi,String> {

    @Query("SELECT diaChi FROM DiaChi diaChi WHERE diaChi.idKhachHang.id = :idKH")
    DiaChi findDiaChiByKhachHangId(@Param("idKH") String idKH);

}
