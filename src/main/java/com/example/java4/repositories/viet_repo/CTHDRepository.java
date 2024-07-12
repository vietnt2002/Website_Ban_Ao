package com.example.java4.repositories.viet_repo;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.response.CTHDResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CTHDRepository extends JpaRepository<ChiTietHoaDon, String> {

    @Query("select new com.example.java4.response.CTHDResponse (cthd.id, ctsp.id, ha.hinhAnh1, ctsp.idSanPham.ten, ctsp.idMauSac.ten, ctsp.idKichThuoc.ten, ctsp.idChatLieu.ten, ctsp.idKieuTay.ten, cthd.soLuong, cthd.donGia, hd.trangThai) " +
            "from ChiTietHoaDon cthd " +
            "join HoaDon hd on hd.id = cthd.idHoaDon.id " +
            "join ChiTietSanPham ctsp on ctsp.id = cthd.idCTSP.id " +
            "join HinhAnh ha on ha.idCTSP.id = ctsp.id " +
            "where hd.id = ?1 ")
    List<CTHDResponse> getAllCTHDByIdHD(String idHD);
}
