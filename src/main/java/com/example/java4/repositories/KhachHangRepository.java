package com.example.java4.repositories;

import com.example.java4.entities.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KhachHangRepository
        extends JpaRepository<KhachHang,String>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public static final int Lock =2;
    public Page<KhachHang> findByTrangThai(int trangThai, Pageable pageable);
//    public Optional<KhachHang> findById(Integer id);
    public Optional<KhachHang> findById(Integer id);

    // Lấy thông tin khách hàng theo Username và Password làm chức năng đăng nhập
    KhachHang findByTaiKhoanAndMatKhau(String taiKhoan, String matKhau);
    KhachHang findByTaiKhoan(String taiKhoan);
    @Query("select kh from KhachHang kh where kh.id = ?1")
    KhachHang findByIdKH(String idKH);
};
