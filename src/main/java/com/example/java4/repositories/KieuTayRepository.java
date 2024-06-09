package com.example.java4.repositories;

import com.example.java4.entities.KieuTay;
import com.example.java4.entities.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuTayRepository extends JpaRepository<KieuTay,String> {

    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<KieuTay> findByTrangThai(int trangThai, Pageable pageable);

}
