package com.example.java4.repositories;

import com.example.java4.entities.NhanVienViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NhanVienRepository_Viet extends JpaRepository<NhanVienViet, String> {
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<NhanVienViet> findByTrangThai(int trangThai, Pageable pageable);
    public Optional<NhanVienViet> findById(String id);

    @Query("select nv from NhanVienViet nv where nv.taiKhoan = ?1")
    NhanVienViet findByTaiKhoan(String tenTK);

    @Query("select nv from NhanVienViet nv order by nv.ngayTao asc ")
    Page<NhanVienViet> getAlll(Pageable pageable);

    //Tìm kiếm theo tên hoặc sdt
    @Query("select nv from NhanVienViet nv where nv.hoTen like %?1% or nv.sdt like %?1%")
    List<NhanVienViet> findByHoTenOrSdt(String key);

    //Tìm kiếm theo chức vụ
    @Query("select nv from NhanVienViet nv where nv.idCV.id = ?1")
    List<NhanVienViet> findByChucVu(String idCV);

    //Tìm kiếm theo tên hoặc sdt và chức vụ
    @Query("select nv from NhanVienViet nv where (nv.hoTen like %?1% or nv.sdt like %?1%) and (nv.idCV.id = ?2)")
    List<NhanVienViet> findByHoTenOrSdtAndChucVu(String key, String idCV);
}
