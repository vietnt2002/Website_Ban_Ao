package com.example.java4.entities.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class HoaDon {
    private String id;
    private String ma;
    private Integer phuongThucThanhToan;
    private KhuyenMai idKhuyenMai;
    private NhanVien idNhanVien;
    private KhachHang idKhachHang;
    private Date ngayTao;
    private Date ngayThanhToan;
    private Integer tongTien;
    private Integer trangThai;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "Ma")
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    @Column(name = "PhuongThucThanhToan")
    public Integer getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(Integer phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    @Column(name = "NgayTao")
    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Column(name = "NgayThanhToan")
    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    @Column(name = "TongTien")
    public Integer getTongTien() {
        return tongTien;
    }

    public void setTongTien(Integer tongTien) {
        this.tongTien = tongTien;
    }

    @Column(name = "TrangThai")
    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoaDon hoaDon = (HoaDon) o;
        return Objects.equals(id, hoaDon.id) && Objects.equals(ma, hoaDon.ma) && Objects.equals(phuongThucThanhToan, hoaDon.phuongThucThanhToan) && Objects.equals(ngayTao, hoaDon.ngayTao) && Objects.equals(ngayThanhToan, hoaDon.ngayThanhToan) && Objects.equals(tongTien, hoaDon.tongTien) && Objects.equals(trangThai, hoaDon.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ma, phuongThucThanhToan, ngayTao, ngayThanhToan, tongTien, trangThai);
    }

    @ManyToOne
    @JoinColumn(name = "IdKhuyenMai", referencedColumnName = "ID")
    public KhuyenMai getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public void setIdKhuyenMai(KhuyenMai khuyenMaiByIdKhuyenMai) {
        this.idKhuyenMai = khuyenMaiByIdKhuyenMai;
    }

    @ManyToOne
    @JoinColumn(name = "IdNhanVien", referencedColumnName = "ID")
    public NhanVien getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(NhanVien nhanVienByIdNhanVien) {
        this.idNhanVien = nhanVienByIdNhanVien;
    }

    @ManyToOne
    @JoinColumn(name = "IdKhachHang", referencedColumnName = "ID")
    public KhachHang getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(KhachHang khachHangByIdKhachHang) {
        this.idKhachHang = khachHangByIdKhachHang;
    }
}
