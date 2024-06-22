package com.example.java4.repositories;

import com.example.java4.entities.NhanVienViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository_Viet extends JpaRepository<NhanVienViet, String> {
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<NhanVienViet> findByTrangThai(int trangThai, Pageable pageable);
    public Optional<NhanVienViet> findById(String id);

    @Query("select nv from NhanVienViet nv where nv.taiKhoan = ?1")
    NhanVienViet findByTaiKhoan(String tenTK);


}
