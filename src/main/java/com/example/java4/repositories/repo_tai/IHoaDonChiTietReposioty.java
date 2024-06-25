package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.tai.HoaDon_Tai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHoaDonChiTietReposioty extends JpaRepository<ChiTietHoaDon,String>{

}
