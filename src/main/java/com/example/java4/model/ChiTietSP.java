package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "ChiTietSP")
public class ChiTietSP {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idChiTietSP;
    @ManyToOne
    @JoinColumn(name="idSanPham")
    private SanPham sp;
    @ManyToOne
    @JoinColumn(name="idNSX")
    private NSX nsx;
    @ManyToOne
    @JoinColumn(name="idMauSac")
    private MauSac mauSac;
    @ManyToOne
    @JoinColumn(name="idDongSP")
    private DongSP dongSP;
    @Column(name="NamBH")
    private int namBH;
    @Column(name="MoTa")
    private String mota;
    @Column(name="SoLuongTon")
    private int soLuongTon;
    @Column(name="GiaNhap")
    private double giaNhap;
    @Column(name="GiaBan")
    private double giaBan;
    @Column(name ="Link")
    private String link;
    public ChiTietSP() {
    }

    public ChiTietSP(String idChiTietSP, SanPham sp, NSX nsx, MauSac mauSac, DongSP dongSP, int namBH, String mota, int soLuongTon, double giaNhap, double giaBan, String link) {
        this.idChiTietSP = idChiTietSP;
        this.sp = sp;
        this.nsx = nsx;
        this.mauSac = mauSac;
        this.dongSP = dongSP;
        this.namBH = namBH;
        this.mota = mota;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.link = link;
    }

    public String getIdChiTietSP() {
        return idChiTietSP;
    }

    public void setIdChiTietSP(String idChiTietSP) {
        this.idChiTietSP = idChiTietSP;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public NSX getNsx() {
        return nsx;
    }

    public void setNsx(NSX nsx) {
        this.nsx = nsx;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public DongSP getDongSP() {
        return dongSP;
    }

    public void setDongSP(DongSP dongSP) {
        this.dongSP = dongSP;
    }

    public int getNamBH() {
        return namBH;
    }

    public void setNamBH(int namBH) {
        this.namBH = namBH;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ChiTietSP{" +
                "idChiTietSP='" + idChiTietSP + '\'' +
                ", sp=" + sp +
                ", nsx=" + nsx +
                ", mauSac=" + mauSac +
                ", dongSP=" + dongSP +
                ", namBH=" + namBH +
                ", mota='" + mota + '\'' +
                ", soLuongTon=" + soLuongTon +
                ", giaNhap=" + giaNhap +
                ", giaBan=" + giaBan +
                ", link='" + link + '\'' +
                '}';
    }
}
