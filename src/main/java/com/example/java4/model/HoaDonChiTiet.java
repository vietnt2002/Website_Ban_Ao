package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGeneratior"
    )
    private String idGioHang;
    @ManyToOne
    @JoinColumn(name = "id")
    private SanPham sp;
    @Column(name = "SoLuong")
    private int soluong;
    @Column(name = "DonGia")
    private double donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String idGioHang, SanPham sp, int soluong, double donGia) {
        this.idGioHang = idGioHang;
        this.sp = sp;
        this.soluong = soluong;
        this.donGia = donGia;
    }

    public String getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(String idGioHang) {
        this.idGioHang = idGioHang;
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
                "idGioHang='" + idGioHang + '\'' +
                ", sp=" + sp +
                ", soluong=" + soluong +
                ", donGia=" + donGia +
                '}';
    }
}
