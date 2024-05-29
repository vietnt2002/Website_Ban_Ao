package com.example.java4.entities.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class NhanVien {
    private String id;
    private String hoTen;
    private Integer gioiTinh;
    private String sdt;
    private String taiKhoan;
    private String matKhau;
    private String anhDaiDien;
    private Date ngayTao;
    private Date ngaySua;
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

    @Column(name = "HoTen")
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @Column(name = "GioiTinh")
    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Column(name = "Sdt")
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Column(name = "TaiKhoan")
    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    @Column(name = "MatKhau")
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Column(name = "AnhDaiDien")
    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    @Column(name = "NgayTao")
    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Column(name = "NgaySua")
    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
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
        NhanVien nhanVien = (NhanVien) o;
        return Objects.equals(id, nhanVien.id) && Objects.equals(hoTen, nhanVien.hoTen) && Objects.equals(gioiTinh, nhanVien.gioiTinh) && Objects.equals(sdt, nhanVien.sdt) && Objects.equals(taiKhoan, nhanVien.taiKhoan) && Objects.equals(matKhau, nhanVien.matKhau) && Objects.equals(anhDaiDien, nhanVien.anhDaiDien) && Objects.equals(ngayTao, nhanVien.ngayTao) && Objects.equals(ngaySua, nhanVien.ngaySua) && Objects.equals(trangThai, nhanVien.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hoTen, gioiTinh, sdt, taiKhoan, matKhau, anhDaiDien, ngayTao, ngaySua, trangThai);
    }
}
