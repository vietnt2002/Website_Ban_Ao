package com.example.java4.repositories.NoMap;

import com.example.java4.entities.noMap.ChiTietSanPhamNoMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SPCTRepoNoMap
        extends JpaRepository<ChiTietSanPhamNoMap,String>
{
};
