package com.example.java4.repositories;

import com.example.java4.entities.ChiTietSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface SPCTRepository extends JpaRepository<ChiTietSanPham,String>, JpaSpecificationExecutor<ChiTietSanPham> {

//    @Query("select new com.example.java4.response.SanPhamChiTietResponse(spct.id, ms.tenMauSac, kt.tenKichThuoc, sp.ten, spct.maSPCT, spct.soLuong, spct.donGia, spct.trangThai)" +
//            "            from SPCT spct join MauSac ms on spct.idMauSac.id = ms.id" +
//            "            join KichThuoc kt on spct.idKichThuoc.id = kt.id \n" +
//            "            join SanPham sp on spct.idSanPham.id = sp.id")
//    List<SanPhamChiTietResponse> getList();

    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<ChiTietSanPham> findByTrangThai(int trangThai, Pageable pageable);


    // Tìm kiếm trên ô text
    @Query("select ctsp from ChiTietSanPham ctsp " +
            "where (ctsp.idSanPham.ma like %?1% or ctsp.idSanPham.ten like %?1%" +
            "or ctsp.idMauSac.ten like %?1% or ctsp.idKichThuoc.ten like %?1%" +
            "or ctsp.idChatLieu.ten like %?1% or ctsp.idKieuTay.ten like %?1%) and ctsp.trangThai=?2")
    Page<ChiTietSanPham> timKiem(String key, int trangThai, Pageable pageable);



    //Lọc theo từng combobox
    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.idSanPham.id = ?1 and ctsp.trangThai = ?2")
    Page<ChiTietSanPham> locCTSPByIdSanPham(String idSanPham, int trangThai, Pageable pageable);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.idMauSac.id = ?1 and ctsp.trangThai = ?2")
    Page<ChiTietSanPham> locCTSPByIdMauSac(String idMauSac, int trangThai, Pageable pageable);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.idKichThuoc.id = ?1 and ctsp.trangThai = ?2")
    Page<ChiTietSanPham> locCTSPByIdKichThuoc(String idKichThuoc, int trangThai, Pageable pageable);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.idChatLieu.id = ?1 and ctsp.trangThai = ?2")
    Page<ChiTietSanPham> locCTSPByIdChatLieu(String idChatLieu, int trangThai, Pageable pageable);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.idKieuTay.id = ?1 and ctsp.trangThai = ?2")
    Page<ChiTietSanPham> locCTSPByIdKieuTay(String idKieuTay, int trangThai, Pageable pageable);


    @Query(value = "select * from ChiTietSanPham " +
            "where idSanPham = :idSanPham and idMauSac = :idMauSac and idKichThuoc = :idKichThuoc " +
            "and idChatLieu = :idChatLieu and idKieuTay = :idKieuTay", nativeQuery = true)
    Page<ChiTietSanPham> filterCombobox(@Param("idSanPham")String idSanPham, @Param("idMauSac")String idMauSac, @Param("idKichThuoc")String idKichThuoc, @Param("idChatLieu")String idChatLieu, @Param("idKieuTay")String idKieuTay, Pageable pageable);
    @Query("SELECT ctsp  FROM ChiTietSanPham ctsp WHERE ctsp.id = ?1 ")
    ChiTietSanPham findByIdCTSP(String id);
};



