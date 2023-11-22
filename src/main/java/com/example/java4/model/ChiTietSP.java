package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "ChiTietSP")
public class ChiTietSP {
    @Id
    @Column(name="id", unique = true, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @OneToMany
    @JoinColumn(name="id")
    private SanPham sp;
    @ManyToOne
    @JoinColumn(name="id")
    private NSX nsx;
    @ManyToOne
    @JoinColumn(name="id")
    private String idMauSac;
    @ManyToOne
    @JoinColumn(name="id")
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

    public ChiTietSP() {
    }

    public ChiTietSP(String id, SanPham sp, NSX nsx, String idMauSac, DongSP dongSP, int namBH, String mota, int soLuongTon, double giaNhap, double giaBan) {
        this.id = id;
        this.sp = sp;
        this.nsx = nsx;
        this.idMauSac = idMauSac;
        this.dongSP = dongSP;
        this.namBH = namBH;
        this.mota = mota;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(String idMauSac) {
        this.idMauSac = idMauSac;
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

    @Override
    public String toString() {
        return "ChiTietSP{" +
                "id='" + id + '\'' +
                ", sp=" + sp +
                ", nsx=" + nsx +
                ", idMauSac='" + idMauSac + '\'' +
                ", dongSP=" + dongSP +
                ", namBH=" + namBH +
                ", mota='" + mota + '\'' +
                ", soLuongTon=" + soLuongTon +
                ", giaNhap=" + giaNhap +
                ", giaBan=" + giaBan +
                '}';
    }
}
