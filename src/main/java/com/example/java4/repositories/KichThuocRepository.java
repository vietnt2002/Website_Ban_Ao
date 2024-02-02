package com.example.java4.repositories;
import com.example.java4.entities.HDCT;
import com.example.java4.entities.KichThuoc;
import com.example.java4.entities.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface KichThuocRepository
        extends JpaRepository<KichThuoc,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<KichThuoc> findByTrangThai(int trangThai, Pageable pageable);
};
