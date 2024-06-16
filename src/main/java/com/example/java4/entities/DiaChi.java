package com.example.java4.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class DiaChi {
    private String id;
    private String diaChiChiTiet;
    private String idPhuongXa;
    private String idQuanHuyen;
    private String idTinhThanh;
    private NhanVien idNhanVien;
    private KhachHang idKhachHang;
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

    @Column(name = "DiaChiChiTiet")
    public String getDiaChiChiTiet() {
        return diaChiChiTiet;
    }

    public void setDiaChiChiTiet(String diaChiChiTiet) {
        this.diaChiChiTiet = diaChiChiTiet;
    }

    @Column(name = "TrangThai")
    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Column(name = "IdPhuongXa")
    public String getIdPhuongXa() {
        return idPhuongXa;
    }

    public void setIdPhuongXa(String idPhuongXa) {
        this.idPhuongXa = idPhuongXa;
    }

    @Column(name = "IdQuanHuyen")
    public String getIdQuanHuyen() {
        return idQuanHuyen;
    }

    public void setIdQuanHuyen(String idQuanHuyen) {
        this.idQuanHuyen = idQuanHuyen;
    }

    @Column(name = "IdTinhThanh")
    public String getIdTinhThanh() {
        return idTinhThanh;
    }

    public void setIdTinhThanh(String idTinhThanh) {
        this.idTinhThanh = idTinhThanh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaChi diaChi = (DiaChi) o;
        return Objects.equals(id, diaChi.id) && Objects.equals(diaChiChiTiet, diaChi.diaChiChiTiet) && Objects.equals(idPhuongXa, diaChi.idPhuongXa) && Objects.equals(idQuanHuyen, diaChi.idQuanHuyen) && Objects.equals(idTinhThanh, diaChi.idTinhThanh) && Objects.equals(trangThai, diaChi.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diaChiChiTiet, trangThai);
    }

    @ManyToOne
    @JoinColumn(name = "IdNV", referencedColumnName = "ID")
    public NhanVien getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(NhanVien nhanVienByIdNv) {
        this.idNhanVien = nhanVienByIdNv;
    }

    @ManyToOne
    @JoinColumn(name = "IdKH", referencedColumnName = "ID")
    public KhachHang getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(KhachHang khachHangByIdKh) {
        this.idKhachHang = khachHangByIdKh;
    }
}
