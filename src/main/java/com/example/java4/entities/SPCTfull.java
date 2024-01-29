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
@Table(name = "SanPhamChiTiet")
public class SPCTfull {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="idMauSac")
    private MauSac mauSac;
    @ManyToOne
    @JoinColumn(name="idKichThuoc")
    private KichThuoc kichThuoc;
    @ManyToOne
    @JoinColumn(name="idSanPham")
    private SanPham sanPham;
    @Column(name="MaSPCT")
    private String maSPCT;
    @Column(name="soLuong")
    private int soLuong;
    @Column(name="donGia")
    private double donGia;
    @Column(name="trangThai")
    private int trangThai;
}