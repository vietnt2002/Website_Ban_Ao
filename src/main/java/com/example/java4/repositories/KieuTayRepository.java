package com.example.java4.repositories;

import com.example.java4.entities.KieuTay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuTayRepository extends JpaRepository<KieuTay,String> {
}
