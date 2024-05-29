package com.example.java4.entities.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class GiaoHang {
    private String id;
    private HoaDon idHoaDon;
    private String tenNguoiNhan;
    private String sdtNguoiNhan;
    private String diaChiChiTiet;
    private PhuongXa idPhuongXa;
    private QuanHuyen idQuanHuyen;
    private TinhThanh idTinhThanh;
    private Date ngayShip;
    private Date ngayNhan;
    private Integer phiShip;
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

    @Column(name = "TenNguoiNhan")
    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    @Column(name = "SdtNguoiNhan")
    public String getSdtNguoiNhan() {
        return sdtNguoiNhan;
    }

    public void setSdtNguoiNhan(String sdtNguoiNhan) {
        this.sdtNguoiNhan = sdtNguoiNhan;
    }

    @Column(name = "DiaChiChiTiet")
    public String getDiaChiChiTiet() {
        return diaChiChiTiet;
    }

    public void setDiaChiChiTiet(String diaChiChiTiet) {
        this.diaChiChiTiet = diaChiChiTiet;
    }

    @Column(name = "NgayShip")
    public Date getNgayShip() {
        return ngayShip;
    }

    public void setNgayShip(Date ngayShip) {
        this.ngayShip = ngayShip;
    }

    @Column(name = "NgayNhan")
    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    @Column(name = "PhiShip")
    public Integer getPhiShip() {
        return phiShip;
    }

    public void setPhiShip(Integer phiShip) {
        this.phiShip = phiShip;
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
        GiaoHang giaoHang = (GiaoHang) o;
        return Objects.equals(id, giaoHang.id) && Objects.equals(tenNguoiNhan, giaoHang.tenNguoiNhan) && Objects.equals(sdtNguoiNhan, giaoHang.sdtNguoiNhan) && Objects.equals(diaChiChiTiet, giaoHang.diaChiChiTiet) && Objects.equals(ngayShip, giaoHang.ngayShip) && Objects.equals(ngayNhan, giaoHang.ngayNhan) && Objects.equals(phiShip, giaoHang.phiShip) && Objects.equals(trangThai, giaoHang.trangThai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenNguoiNhan, sdtNguoiNhan, diaChiChiTiet, ngayShip, ngayNhan, phiShip, trangThai);
    }

    @OneToOne
    @JoinColumn(name = "IdHoaDon", referencedColumnName = "ID")
    public HoaDon getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(HoaDon hoaDonByIdHoaDon) {
        this.idHoaDon = hoaDonByIdHoaDon;
    }

    @ManyToOne
    @JoinColumn(name = "IdPhuongXa", referencedColumnName = "ID")
    public PhuongXa getIdPhuongXa() {
        return idPhuongXa;
    }

    public void setIdPhuongXa(PhuongXa phuongXaByIdPhuongXa) {
        this.idPhuongXa = phuongXaByIdPhuongXa;
    }

    @ManyToOne
    @JoinColumn(name = "IdQuanHuyen", referencedColumnName = "ID")
    public QuanHuyen getIdQuanHuyen() {
        return idQuanHuyen;
    }

    public void setIdQuanHuyen(QuanHuyen quanHuyenByIdQuanHuyen) {
        this.idQuanHuyen = quanHuyenByIdQuanHuyen;
    }

    @ManyToOne
    @JoinColumn(name = "IdTinhThanh", referencedColumnName = "ID")
    public TinhThanh getIdTinhThanh() {
        return idTinhThanh;
    }

    public void setIdTinhThanh(TinhThanh tinhThanhByIdTinhThanh) {
        this.idTinhThanh = tinhThanhByIdTinhThanh;
    }
}
