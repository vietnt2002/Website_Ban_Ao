package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.tai.HoaDon_Tai;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHoaDonChiTietReposioty extends JpaRepository<ChiTietHoaDon,String>{
    // Xóa sản phẩm chi tiết từ hóa đơn dựa trên idHoaDon và idCTSP
    @Transactional
    @Modifying
    @Query("DELETE FROM ChiTietHoaDon hdct WHERE hdct.idHoaDon.id = :idHoaDon AND hdct.idCTSP.id = :idCTSP")
    int deleteByHoaDon_IdAndIdCTSP_Id(@Param("idHoaDon") String idHoaDon, @Param("idCTSP") String idCTSP);
}
