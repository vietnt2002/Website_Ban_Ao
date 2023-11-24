package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "GioHangChiTiet")
public class GioHangChiTiet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idGioHangChiTiet;
    @Column(name = "IdChiTietSP")
    private ChiTietSP chiTietSP;
    @Column(name = "SoLuong")
    private int soLuong;
    @Column(name = "DonGia")
    private double donGia;
    @Column(name = "DonGiaKhiGiam")
    private double donGiaKhiGiam;

    public GioHangChiTiet() {
    }

    public GioHangChiTiet(String idGioHangChiTiet, ChiTietSP chiTietSP, int soLuong, double donGia, double donGiaKhiGiam) {
        this.idGioHangChiTiet = idGioHangChiTiet;
        this.chiTietSP = chiTietSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.donGiaKhiGiam = donGiaKhiGiam;
    }

    public String getIdGioHangChiTiet() {
        return idGioHangChiTiet;
    }

    public void setIdGioHangChiTiet(String idGioHangChiTiet) {
        this.idGioHangChiTiet = idGioHangChiTiet;
    }

    public ChiTietSP getChiTietSP() {
        return chiTietSP;
    }

    public void setChiTietSP(ChiTietSP chiTietSP) {
        this.chiTietSP = chiTietSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getDonGiaKhiGiam() {
        return donGiaKhiGiam;
    }

    public void setDonGiaKhiGiam(double donGiaKhiGiam) {
        this.donGiaKhiGiam = donGiaKhiGiam;
    }

    @Override
    public String toString() {
        return "GioHangChiTiet{" +
                "idGioHangChiTiet='" + idGioHangChiTiet + '\'' +
                ", chiTietSP=" + chiTietSP +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", donGiaKhiGiam=" + donGiaKhiGiam +
                '}';
    }
}
