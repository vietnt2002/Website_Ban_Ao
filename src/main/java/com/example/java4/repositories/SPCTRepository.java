package com.example.java4.repositories;
import com.example.java4.entities.SPCT;
import com.example.java4.entities.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPCTRepository
        extends JpaRepository<SPCT,Integer>
{

};
