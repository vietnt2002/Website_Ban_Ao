//package com.example.java4.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//
//import java.io.Serializable;
//
//@Data
//@Entity
//@Table(name = "GioHangChiTiet")
//public class GioHangChiTiet implements Serializable {
//    @EmbeddedId
//    @ManyToOne
//    @JoinColumn(name = "idGioHang")
//    private GioHang gioHang;
//    @EmbeddedId
//    @ManyToOne
//    @JoinColumn(name = "idChiTietSP")
//    private ChiTietSP chiTietSP;
//    @Column(name = "SoLuong")
//    private int soLuong;
//    @Column(name = "DonGia")
//    private double donGia;
//    @Column(name = "DonGiaKhiGiam")
//    private double donGiaKhiGiam;
//
//    public GioHangChiTiet() {
//    }
//
//    public GioHangChiTiet(GioHang gioHang, ChiTietSP chiTietSP, int soLuong, double donGia, double donGiaKhiGiam) {
//        this.gioHang = gioHang;
//        this.chiTietSP = chiTietSP;
//        this.soLuong = soLuong;
//        this.donGia = donGia;
//        this.donGiaKhiGiam = donGiaKhiGiam;
//    }
//
//    public GioHang getGioHang() {
//        return gioHang;
//    }
//
//    public void setGioHang(GioHang gioHang) {
//        this.gioHang = gioHang;
//    }
//
//    public ChiTietSP getChiTietSP() {
//        return chiTietSP;
//    }
//
//    public void setChiTietSP(ChiTietSP chiTietSP) {
//        this.chiTietSP = chiTietSP;
//    }
//
//    public int getSoLuong() {
//        return soLuong;
//    }
//
//    public void setSoLuong(int soLuong) {
//        this.soLuong = soLuong;
//    }
//
//    public double getDonGia() {
//        return donGia;
//    }
//
//    public void setDonGia(double donGia) {
//        this.donGia = donGia;
//    }
//
//    public double getDonGiaKhiGiam() {
//        return donGiaKhiGiam;
//    }
//
//    public void setDonGiaKhiGiam(double donGiaKhiGiam) {
//        this.donGiaKhiGiam = donGiaKhiGiam;
//    }
//
//    @Override
//    public String toString() {
//        return "GioHangChiTiet{" +
//                "gioHang=" + gioHang +
//                ", chiTietSP=" + chiTietSP +
//                ", soLuong=" + soLuong +
//                ", donGia=" + donGia +
//                ", donGiaKhiGiam=" + donGiaKhiGiam +
//                '}';
//    }
//}
