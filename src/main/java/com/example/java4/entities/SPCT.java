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
public class SPCT {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="IdMauSac")
    private Integer idMauSac;
    @Column(name="IdKichThuoc")
    private Integer idKichThuoc;
    @Column(name="IdSanPham")
    private Integer idSanPham;
    @Column(name="MaSPCT")
    private String maSPCT;
    @Column(name="soLuong")
    private int soLuong;
    @Column(name="donGia")
    private double donGia;
    @Column(name="trangThai")
    private int trangThai;
}