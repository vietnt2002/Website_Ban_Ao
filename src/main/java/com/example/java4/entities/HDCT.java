package com.example.java4.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="HoaDonChiTiet")
public class HDCT {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="IdHoaDon")
    private HoaDon hoaDon;
    @ManyToOne
    @JoinColumn(name="IdSPCT")
    private SPCT spct;
    @Column(name="SoLuong")
    private int soLuong;
    @Column(name="DonGia")
    private double donGia;
    @Column(name="ThoiGian")
    private Timestamp thoiGian;
    @Column(name="TrangThai")
    private Integer trangThai;
}