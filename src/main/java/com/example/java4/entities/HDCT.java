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
    @Column(name="IdHoaDon")
    private Integer idHoaDon;
    @Column(name="IdSPCT")
    private Integer idSPCT;
    @Column(name="SoLuong")
    private int soLuong;
    @Column(name="DonGia")
    private int donGia;
    @Column(name="ThoiGian")
    private Timestamp thoiGian;
    @Column(name="TrangThai")
    private Integer trangThai;
}