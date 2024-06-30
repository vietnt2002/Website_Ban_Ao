package com.example.java4.repositories;

import com.example.java4.entities.MauSac;
import com.example.java4.entities.MauSac;
import com.example.java4.response.KichThuocRespone;
import com.example.java4.response.MauSacRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MauSacRepository
        extends JpaRepository<MauSac,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    //Lấy ra danh sách màu sắc theo idSP
    @Query("select distinct new com.example.java4.response.MauSacRespone(ms.id, ms.ma, ms.ten)  " +
            "from ChiTietSanPham ctsp " +
            "join SanPham sp on sp.id = ctsp.idSanPham.id " +
            "join MauSac ms on ms.id = ctsp.idMauSac.id " +
            "where sp.id = ?1 " +
            "order by ms.ma asc ")
    List<MauSacRespone> getListMauSacByIdSP(String idSP);
    //Lấy ra màu sắc theo tên màu sắc
    MauSac findByTen(String tenMS);
    @Query(value = "select ms from MauSac ms ORDER BY ms.ngayTao asc")
    Page<MauSac> findByTrangThai(int trangThai, Pageable pageable);
    @Query(value = "select ms from MauSac ms ORDER BY ms.ngayTao asc")
    Page<MauSac> findAllByPage(Pageable pageable);
    @Query("UPDATE MauSac ms SET ms.trangThai = 1 WHERE ms.id=:id")
    int enableStt(@Param("id") String id);
    @Query("UPDATE MauSac ms SET ms.trangThai = 0 WHERE ms.id=:id")
    int disableStt(@Param("id")String id);
    @Query(value = "SELECT COUNT(*) FROM mausac",nativeQuery = true)
    Integer getCount();
    @Query(value = "SELECT COUNT(*) FROM mausac where trangThai=1",nativeQuery = true)
    Integer getCountStt1();
    @Query(value = "SELECT COUNT(*) FROM mausac where trangThai=0",nativeQuery = true)
    Integer getCountStt0();
};
