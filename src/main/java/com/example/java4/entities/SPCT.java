package com.example.java4.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SPCT")

public class SPCT {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="idMauSac")
    private MauSac idMauSac;

    @ManyToOne
    @JoinColumn(name="idKichThuoc")
    private KichThuoc idKichThuoc;

    @ManyToOne
    @JoinColumn(name="idSanPham")
    private SanPham idSanPham;

    @Column(name="MaSPCT")
    private String maSPCT;

    @Column(name="soLuong")
    private int soLuong;

    @Column(name="donGia")
    private double donGia;

    @Column(name="trangThai")
    private int trangThai;
}