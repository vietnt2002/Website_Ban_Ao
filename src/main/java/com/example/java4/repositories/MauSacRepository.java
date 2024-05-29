package com.example.java4.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository
        extends JpaRepository<MauSac,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<MauSac> findByTrangThai(int trangThai, Pageable pageable);
} ;
