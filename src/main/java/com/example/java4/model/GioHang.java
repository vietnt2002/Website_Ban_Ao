//package com.example.java4.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//
//import java.io.Serializable;
//import java.sql.Date;
//@Data
//@Entity
//@Table(name = "GioHang")
//public class GioHang implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String idGioHang;
//    @ManyToOne
//    @JoinColumn(name = "idKhachHang")
//    private KhachHang kh;
//    @ManyToOne
//    @JoinColumn(name = "idNhanVien")
//    private NhanVien nv;
//    @Column(name = "Ma")
//    private String ma;
//    @Column(name = "NgayTao")
//    private Date ngayTao;
//    @Column(name = "NgayThanhToan")
//    private Date ngayThanhToan;
//    @Column(name = "TenNguoiNhan")
//    private String tenNguoiNhan;
//    @Column(name = "DiaChi")
//    private String diaChi;
//    @Column(name = "Sdt")
//    private String sdt;
//    @Column(name = "TinhTrang")
//    private int tinhTrang;
//
//    public GioHang() {
//    }
//
//    public GioHang(String idGioHang, KhachHang kh, NhanVien nv, String ma, Date ngayTao, Date ngayThanhToan, String tenNguoiNhan, String diaChi, String sdt, int tinhTrang) {
//        this.idGioHang = idGioHang;
//        this.kh = kh;
//        this.nv = nv;
//        this.ma = ma;
//        this.ngayTao = ngayTao;
//        this.ngayThanhToan = ngayThanhToan;
//        this.tenNguoiNhan = tenNguoiNhan;
//        this.diaChi = diaChi;
//        this.sdt = sdt;
//        this.tinhTrang = tinhTrang;
//    }
//
//    public String getIdGioHang() {
//        return idGioHang;
//    }
//
//    public void setIdGioHang(String idGioHang) {
//        this.idGioHang = idGioHang;
//    }
//
//    public KhachHang getKh() {
//        return kh;
//    }
//
//    public void setKh(KhachHang kh) {
//        this.kh = kh;
//    }
//
//    public NhanVien getNv() {
//        return nv;
//    }
//
//    public void setNv(NhanVien nv) {
//        this.nv = nv;
//    }
//
//    public String getMa() {
//        return ma;
//    }
//
//    public void setMa(String ma) {
//        this.ma = ma;
//    }
//
//    public Date getNgayTao() {
//        return ngayTao;
//    }
//
//    public void setNgayTao(Date ngayTao) {
//        this.ngayTao = ngayTao;
//    }
//
//    public Date getNgayThanhToan() {
//        return ngayThanhToan;
//    }
//
//    public void setNgayThanhToan(Date ngayThanhToan) {
//        this.ngayThanhToan = ngayThanhToan;
//    }
//
//    public String getTenNguoiNhan() {
//        return tenNguoiNhan;
//    }
//
//    public void setTenNguoiNhan(String tenNguoiNhan) {
//        this.tenNguoiNhan = tenNguoiNhan;
//    }
//
//    public String getDiaChi() {
//        return diaChi;
//    }
//
//    public void setDiaChi(String diaChi) {
//        this.diaChi = diaChi;
//    }
//
//    public String getSdt() {
//        return sdt;
//    }
//
//    public void setSdt(String sdt) {
//        this.sdt = sdt;
//    }
//
//    public int getTinhTrang() {
//        return tinhTrang;
//    }
//
//    public void setTinhTrang(int tinhTrang) {
//        this.tinhTrang = tinhTrang;
//    }
//
//    @Override
//    public String toString() {
//        return "GioHang{" +
//                "idGioHang='" + idGioHang + '\'' +
//                ", kh=" + kh +
//                ", nv=" + nv +
//                ", ma='" + ma + '\'' +
//                ", ngayTao=" + ngayTao +
//                ", ngayThanhToan=" + ngayThanhToan +
//                ", tenNguoiNhan='" + tenNguoiNhan + '\'' +
//                ", diaChi='" + diaChi + '\'' +
//                ", sdt='" + sdt + '\'' +
//                ", tinhTrang=" + tinhTrang +
//                '}';
//    }
//}
