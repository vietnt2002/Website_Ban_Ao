package com.example.java4.repositories;
import com.example.java4.entities.MauSac;
import com.example.java4.entities.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository
        extends JpaRepository<SanPham,Integer>
{

};
