package com.example.java4.repositories;
import com.example.java4.entities.HoaDon;
import com.example.java4.entities.HoaDonFull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonFullRepository
        extends JpaRepository<HoaDonFull,Integer>
{

};
