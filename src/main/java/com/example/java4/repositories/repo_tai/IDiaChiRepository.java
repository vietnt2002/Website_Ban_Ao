package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiaChiRepository extends JpaRepository<DiaChi,String> {

    DiaChi findByIdKhachHang_Id(String idKH);

}
