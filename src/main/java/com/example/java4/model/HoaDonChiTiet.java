package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet implements Serializable {
    @ManyToOne
    @JoinColumn(name = "idHoaDon")
    private HoaDon hoaDon;
    @ManyToOne
    @JoinColumn(name = "idSanPham")
    private SanPham sp;
    @Column(name = "SoLuong")
    private int soluong;
    @Column(name = "DonGia")
    private double donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(HoaDon hoaDon, SanPham sp, int soluong, double donGia) {
        this.hoaDon = hoaDon;
        this.sp = sp;
        this.soluong = soluong;
        this.donGia = donGia;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "hoaDon=" + hoaDon +
                ", sp=" + sp +
                ", soluong=" + soluong +
                ", donGia=" + donGia +
                '}';
    }
}
