package com.example.java4.entities.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class ChiTietSanPham {
    private String id;
    private Integer soLuong;
    private String moTa;
    private Integer giaNhap;
    private Integer giaBan;
    private Date ngayTao;
    private Integer trangThai;
    private MauSac idMauSac;
    private KichThuoc idKichThuoc;
    private ChatLieu idChatLieu;
    private KieuTay idKieuTay;
    private SanPham idSanPham;

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

    @Column(name = "MoTa")
    public String getMoTa() {
        return moTa;
    }
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Column(name = "GiaNhap")
    public Integer getGiaNhap() {
        return giaNhap;
    }
    public void setGiaNhap(Integer giaNhap) {
        this.giaNhap = giaNhap;
    }

    @Column(name = "GiaBan")
    public Integer getGiaBan() {
        return giaBan;
    }
    public void setGiaBan(Integer giaBan) {
        this.giaBan = giaBan;
    }

    @Column(name = "NgayTao")
    public Date getNgayTao() {
        return ngayTao;
    }
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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
        ChiTietSanPham that = (ChiTietSanPham) o;
        return Objects.equals(id, that.id) && Objects.equals(soLuong, that.soLuong) && Objects.equals(moTa, that.moTa) && Objects.equals(giaNhap, that.giaNhap) && Objects.equals(giaBan, that.giaBan) && Objects.equals(ngayTao, that.ngayTao) && Objects.equals(trangThai, that.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, soLuong, moTa, giaNhap, giaBan, ngayTao, trangThai);
    }

    @ManyToOne
    @JoinColumn(name = "IdMauSac", referencedColumnName = "ID")
    public MauSac getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(MauSac mauSacByIdMauSac) {
        this.idMauSac = mauSacByIdMauSac;
    }

    @ManyToOne
    @JoinColumn(name = "IdKichThuoc", referencedColumnName = "ID")
    public KichThuoc getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(KichThuoc kichThuocByIdKichThuoc) {
        this.idKichThuoc = kichThuocByIdKichThuoc;
    }

    @ManyToOne
    @JoinColumn(name = "IdChatLieu", referencedColumnName = "ID")
    public ChatLieu getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(ChatLieu chatLieuByIdChatLieu) {
        this.idChatLieu = chatLieuByIdChatLieu;
    }

    @ManyToOne
    @JoinColumn(name = "IdKieuTay", referencedColumnName = "ID")
    public KieuTay getIdKieuTay() {
        return idKieuTay;
    }

    public void setIdKieuTay(KieuTay kieuTayByIdKieuTay) {
        this.idKieuTay = kieuTayByIdKieuTay;
    }

    @ManyToOne
    @JoinColumn(name = "IdSanPham", referencedColumnName = "ID")
    public SanPham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(SanPham sanPhamByIdSanPham) {
        this.idSanPham = sanPhamByIdSanPham;
    }
}
