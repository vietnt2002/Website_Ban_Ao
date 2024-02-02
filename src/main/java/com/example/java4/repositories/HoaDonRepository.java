package com.example.java4.repositories;
import com.example.java4.entities.HDCT;
import com.example.java4.entities.HoaDon;
import com.example.java4.entities.KichThuoc;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface HoaDonRepository
        extends JpaRepository<HoaDon,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<HoaDon> findByTrangThai(int trangThai, Pageable pageable);
    @Query(value = "SELECT TOP 5 * FROM HoaDonChiTiet ORDER BY ID DESC",
           nativeQuery = true)
    public List<HoaDon> findByTrangThai(int trangThai);
};
