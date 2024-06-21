package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.GiaoHang;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IGiaoHangRepository extends JpaRepository<GiaoHang,String> {

    GiaoHang findByIdHoaDon_Id(String idHoaDon);


}
