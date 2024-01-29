package com.example.java4.repositories;
import com.example.java4.entities.KhachHang;
import com.example.java4.entities.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository
        extends JpaRepository<NhanVien,Integer>
{

};
