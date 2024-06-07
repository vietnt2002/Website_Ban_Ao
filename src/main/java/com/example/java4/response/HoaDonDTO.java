package com.example.java4.response;


import com.example.java4.entities.DiaChi;
import com.example.java4.entities.KhachHang;
import com.example.java4.entities.KhuyenMai;
import com.example.java4.entities.NhanVien;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonDTO {

    private String id;
    private String ma;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private DiaChi diaChi;
    private String loaiHoaDon;
    private Integer phuongThucThanhToan;
    private Integer tongTien;
    private String ngayTao;
    private Integer trangThai;





    public HoaDonDTO(String id, String ma, KhachHang khachHang, NhanVien nhanVien, Integer phuongThucThanhToan, Integer tongTien, String ngayTao, Integer trangThai) {
        this.id = id;
        this.ma = ma;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }


}


