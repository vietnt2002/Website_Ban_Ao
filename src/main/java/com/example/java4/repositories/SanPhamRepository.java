package com.example.java4.repositories;
import com.example.java4.entities.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface SanPhamRepository
        extends JpaRepository<SanPham,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<SanPham> findByTrangThai(int trangThai, Pageable pageable);
};
