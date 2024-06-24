package com.example.java4.repositories;
import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.response.MauSizeSL;
import com.example.java4.response.SPCTResponse;
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
    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.trangThai=1 order by ctsp.id asc")
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
    //Lọc cộng dồn sản phẩm(chưa được)
    @Query(value = "select * from ChiTietSanPham " +
            "where idSanPham = :idSanPham and idMauSac = :idMauSac and idKichThuoc = :idKichThuoc " +
            "and idChatLieu = :idChatLieu and idKieuTay = :idKieuTay", nativeQuery = true)
    Page<ChiTietSanPham> filterCombobox(@Param("idSanPham")String idSanPham, @Param("idMauSac")String idMauSac, @Param("idKichThuoc")String idKichThuoc, @Param("idChatLieu")String idChatLieu, @Param("idKieuTay")String idKieuTay, Pageable pageable);

    @Query("SELECT ctsp  FROM ChiTietSanPham ctsp WHERE ctsp.id = ?1 ")
    ChiTietSanPham findByIdCTSP(String id);

    //Lấy danh sách sản phẩm hiển thị trang chủ(online)
//    @Query("SELECT new com.example.java4.response.SPCTResponse(sp.id, sp.ma, sp.ten, kta.ten, MAX(ctsp.giaBan), MAX(ha.hinhAnh1))" +
//            "from ChiTietSanPham ctsp " +
//            "join SanPham sp on sp.id = ctsp.idSanPham.id " +
//            "join MauSac ms on ms.id = ctsp.idMauSac.id " +
//            "join KichThuoc kth on  kth.id = ctsp.idKichThuoc.id " +
//            "join ChatLieu cl on cl.id = ctsp.idChatLieu.id " +
//            "join KieuTay kta on kta.id = ctsp.idKieuTay.id " +
//            "join HinhAnh ha on ctsp.id = ha.idCTSP.id " +
//            "group by sp.id, sp.ma, sp.ten, kta.ten")
//    Page<SPCTResponse> getAllSP(Pageable pageable);

    @Query("SELECT new com.example.java4.response.SPCTResponse(ctsp.id, sp.id, sp.ma, sp.ten, kta.ten, ctsp.giaBan, ha.hinhAnh1)" +
            "from ChiTietSanPham ctsp " +
            "join SanPham sp on sp.id = ctsp.idSanPham.id " +
            "join MauSac ms on ms.id = ctsp.idMauSac.id " +
            "join KichThuoc kth on  kth.id = ctsp.idKichThuoc.id " +
            "join ChatLieu cl on cl.id = ctsp.idChatLieu.id " +
            "join KieuTay kta on kta.id = ctsp.idKieuTay.id " +
            "join HinhAnh ha on ctsp.id = ha.idCTSP.id " +
            "where ctsp.id in (select min (innerCtsp.id) from ChiTietSanPham innerCtsp group by innerCtsp.idSanPham.id)")
    Page<SPCTResponse> getAllSP(Pageable pageable);

    //Lấy danh sách màu sắc, kích thước, số lượng để sang js lọc
    @Query("select new com.example.java4.response.MauSizeSL (kth.ten, ms.ten, ctsp.soLuong) " +
            "from ChiTietSanPham ctsp " +
            "join KichThuoc kth on kth.id = ctsp.idKichThuoc.id " +
            "join SanPham sp on sp.id = ctsp.idSanPham.id " +
            "join MauSac ms on ms.id = ctsp.idMauSac.id " +
            "where sp.id = ?1")
    List<MauSizeSL> getListMauSizeSL(String idSP);

    //Lấy ra số lượng ctsp bên trang chi tiết
    @Query("select ctsp.soLuong from ChiTietSanPham ctsp where ctsp.idSanPham.id = ?1 and ctsp.idMauSac.id = ?2 and ctsp.idKichThuoc.id = ?3")
    Integer getSoLuongCTSPBySanPham_MauSac_KichThuoc(String idSP, String idMS, String idKth);

    //Lấy ra STSP theo idSP, idMS, idKth
    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.idSanPham.id = ?1 and ctsp.idMauSac.id = ?2 and ctsp.idKichThuoc.id = ?3")
    ChiTietSanPham findCTSPBySanPham_MauSac_KichThuoc(String idSP, String idMS, String idKth);

    @Query("SELECT spct.soLuong FROM ChiTietSanPham spct WHERE spct.id = :idSPCT AND spct.trangThai = 1")
    Integer findBySoLuong(@Param("idSPCT") String idSPCT);
};



