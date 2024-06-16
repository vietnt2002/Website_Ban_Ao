package com.example.java4.repositories;

import com.example.java4.entities.GiaoHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiaoHangRepo extends JpaRepository<GiaoHang, String> {
}