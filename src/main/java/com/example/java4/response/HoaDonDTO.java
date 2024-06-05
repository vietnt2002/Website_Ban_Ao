package com.example.java4.response;


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
    private String tenNhanVien;
    private String tenKhachHang;
    private String sdtKhachHang;
    private String loaiHoaDon;
    private Integer phuongThucThanhToan;
    private Integer tongTien;
    private String ngayTao;
    private Integer trangThai;

    public HoaDonDTO(String id, String ma, String tenNhanVien, String tenKhachHang, String sdtKhachHang, Integer phuongThucThanhToan, Integer tongTien, String ngayTao, Integer trangThai) {
        this.id = id;
        this.ma = ma;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.sdtKhachHang = sdtKhachHang;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }
}


