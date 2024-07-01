package com.example.java4.repositories;

import com.example.java4.entities.ChatLieu;
import com.example.java4.entities.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ChatLieuRepository extends JpaRepository<ChatLieu, String> {
    int ACTIVE  = 1;
    int INACTIVE =0;
    @Query(value = "select cl from ChatLieu cl ORDER BY cl.ngayTao asc")
    Page<ChatLieu> findByTrangThai(int trangThai, Pageable pageable);
    @Query(value = "select cl from ChatLieu cl ORDER BY cl.ngayTao asc")
    Page<ChatLieu> findAllByPage(Pageable pageable);
    @Query("UPDATE ChatLieu cl SET cl.trangThai = 1 WHERE cl.id=:id")
    int enableStt(@Param("id") String id);
    @Query("UPDATE ChatLieu cl SET cl.trangThai = 0 WHERE cl.id=:id")
    int disableStt(@Param("id")String id);
    @Query(value = "SELECT COUNT(*) FROM chatlieu",nativeQuery = true)
    Integer getCount();
    @Query(value = "SELECT COUNT(*) FROM chatlieu where trangThai=1",nativeQuery = true)
    Integer getCountStt1();
    @Query(value = "SELECT COUNT(*) FROM chatlieu where trangThai=0",nativeQuery = true)
    Integer getCountStt0();
}
