package com.example.java4.repositories;

import com.example.java4.entities.KichThuoc;
import com.example.java4.entities.KichThuoc;
import com.example.java4.response.KichThuocRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KichThuocRepository
        extends JpaRepository<KichThuoc,String>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    //Lấy ra danh sách kích thước theo idSP
    @Query("select distinct new com.example.java4.response.KichThuocRespone(kth.id, kth.ma, kth.ten)  " +
            "from ChiTietSanPham ctsp " +
            "join SanPham sp on sp.id = ctsp.idSanPham.id " +
            "join KichThuoc kth on kth.id = ctsp.idKichThuoc.id " +
            "where sp.id = ?1 " +
            "order by kth.ma asc ")
    List<KichThuocRespone> getListKichThuocByIdSP(String idSP);
    //Lấy ra kích thước theo tên kích thước
    KichThuoc findByTen(String tenKth);
    @Query(value = "select kt from KichThuoc kt ORDER BY kt.ngayTao asc")
    Page<KichThuoc> findByTrangThai(int trangThai, Pageable pageable);
    @Query(value = "select kt from KichThuoc kt ORDER BY kt.ngayTao asc")
    Page<KichThuoc> findAllByPage(Pageable pageable);
    @Query("UPDATE KichThuoc kt SET kt.trangThai = 1 WHERE kt.id=:id")
    int enableStt(@Param("id") String id);
    @Query("UPDATE KichThuoc kt SET kt.trangThai = 0 WHERE kt.id=:id")
    int disableStt(@Param("id")String id);
    @Query(value = "SELECT COUNT(*) FROM kichthuoc",nativeQuery = true)
    Integer getCount();
    @Query(value = "SELECT COUNT(*) FROM kichthuoc where trangThai=1",nativeQuery = true)
    Integer getCountStt1();
    @Query(value = "SELECT COUNT(*) FROM kichthuoc where trangThai=0",nativeQuery = true)
    Integer getCountStt0();
};
