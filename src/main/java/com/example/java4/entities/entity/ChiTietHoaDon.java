package com.example.java4.entities.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ChiTietHoaDon {
    private String id;
    private Integer soLuong;
    private Integer donGia;
    private Integer trangThai;
    private Integer hdctOld; // dùng để làm đổi trả
    private HoaDon idHoaDon;
    private ChiTietSanPham idCTSP;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "SoLuong")
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    @Column(name = "DonGia")
    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
    }

    @Column(name = "TrangThai")
    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Column(name = "HDCT_Old")
    public Integer getHdctOld() {
        return hdctOld;
    }

    public void setHdctOld(Integer hdctOld) {
        this.hdctOld = hdctOld;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDon that = (ChiTietHoaDon) o;
        return Objects.equals(id, that.id) && Objects.equals(soLuong, that.soLuong) && Objects.equals(donGia, that.donGia) && Objects.equals(trangThai, that.trangThai) && Objects.equals(hdctOld, that.hdctOld);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, soLuong, donGia, trangThai, hdctOld);
    }

    @ManyToOne
    @JoinColumn(name = "IdHoaDon", referencedColumnName = "ID")
    public HoaDon getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(HoaDon hoaDonByIdHoaDon) {
        this.idHoaDon = hoaDonByIdHoaDon;
    }

    @ManyToOne
    @JoinColumn(name = "IdCTSP", referencedColumnName = "ID")
    public ChiTietSanPham getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(ChiTietSanPham chiTietSanPhamByIdCtsp) {
        this.idCTSP = chiTietSanPhamByIdCtsp;
    }
}
