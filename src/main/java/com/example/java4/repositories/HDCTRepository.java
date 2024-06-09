package com.example.java4.repositories;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.ChiTietSanPham;
import com.example.java4.response.HoaDonChiTietDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface HDCTRepository
        extends JpaRepository<ChiTietHoaDon,String>
{
        public static final int ACTIVE = 1;
        public static final int INACTIVE = 0;
        public Page<ChiTietHoaDon> findByTrangThai(int trangThai, Pageable pageable);
        @Query ("SELECT h FROM ChiTietHoaDon h WHERE h.idHoaDon.id = :hoaDon AND h.idCTSP = :sanPhamChiTiet")
        ChiTietHoaDon findByHoaDonAndIdSanPhamChiTiet(@PathVariable ("hoaDon") String hoaDon, @PathVariable ("sanPhamChiTiet") ChiTietSanPham sanPhamChiTiet);



        // Lấy ra danh sách HoaDonChiTiet theo ID hóa đơn
        @Query("SELECT hdct FROM ChiTietHoaDon hdct WHERE hdct.idHoaDon.id = :hoaDonId")
        List<ChiTietHoaDon> findAllByHoaDon_Id(@Param("hoaDonId") String hoaDonId);

        // Lấy ra tổng tiền trong hóa đơn chi tiết
        @Query("SELECT SUM (hdct.soLuong * hdct.donGia) FROM ChiTietHoaDon  hdct WHERE hdct.idHoaDon.id = :hoaDonId" )
        public Integer tinhGiaTriHD(@Param("hoaDonId") String hoaDonId);

//        public static final int ACTIVE = 1;
//        public static final int INACTIVE = 0;
//        public Page<ChiTietHoaDon> findByTrangThai(int trangThai, Pageable pageable);
//        public List<ChiTietHoaDon> findAllByHoaDon_Id(String id);
//        @Query ("SELECT h FROM ChiTietHoaDon h WHERE h.idHoaDon.id = :hoaDon AND h.idCTSP = :sanPhamChiTiet")
//        ChiTietHoaDon findByHoaDonAndIdSanPhamChiTiet(@PathVariable ("hoaDon") String hoaDon, @PathVariable ("sanPhamChiTiet") ChiTietSanPham sanPhamChiTiet);
//

    public static final int CHUA_THANH_TOAN = 0;
    public static final int DA_THANH_TOAN = 1;

    @Query("select hdct from ChiTietHoaDon hdct where hdct.idHoaDon.id = ?1")
    List<ChiTietHoaDon> findHDCTByIdHoaDon(String idHD);
};
