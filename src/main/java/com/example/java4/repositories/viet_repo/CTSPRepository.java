package com.example.java4.repositories.viet_repo;

import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.response.ChiTietSanPhamResponse;
import com.example.java4.response.SPCTResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CTSPRepository extends JpaRepository<ChiTietSanPham, String>, JpaSpecificationExecutor<ChiTietSanPham> {

    @Query("select min(ctsp.giaBan) from ChiTietSanPham ctsp")
    BigDecimal minGiaBan();

    @Query("select max(ctsp.giaBan) from ChiTietSanPham ctsp")
    BigDecimal maxGiaBan();

    @Query("select new com.example.java4.response.ChiTietSanPhamResponse(ctsp.id, sp.id, ms.ten, kth.ten, kta.ten, ctsp.soLuong, ctsp.giaBan, ha.hinhAnh1) " +
            "from ChiTietSanPham ctsp " +
            "join SanPham sp on sp.id = ctsp.idSanPham.id " +
            "join MauSac ms on ms.id = ctsp.idMauSac.id " +
            "join KichThuoc kth on kth.id = ctsp.idKichThuoc.id " +
            "join KieuTay kta on kta.id = ctsp.idKieuTay.id " +
            "join HinhAnh ha on ha.idCTSP.id = ctsp.id ")
    List<ChiTietSanPhamResponse> getCTSPRes();

}
