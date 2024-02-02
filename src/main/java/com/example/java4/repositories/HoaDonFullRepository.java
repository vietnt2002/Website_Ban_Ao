package com.example.java4.repositories;
import com.example.java4.entities.HDCT;
import com.example.java4.entities.HDCTfull;
import com.example.java4.entities.HoaDon;
import com.example.java4.entities.HoaDonFull;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonFullRepository
        extends JpaRepository<HoaDonFull,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<HoaDonFull> findByTrangThai(int trangThai, Pageable pageable);
    @Query(value = "SELECT TOP 5 * FROM HoaDon where trangThai = 0 ORDER BY ID DESC ",
            nativeQuery = true)
    public List<HoaDonFull>  selectTop5();
    public Optional<HoaDonFull> findById(Integer id);
};
