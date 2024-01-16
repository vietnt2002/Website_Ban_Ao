package com.example.java4.model;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SPCT {
    private String id;
    private String maSPCT;
    private KichThuoc kichThuoc;
    private MauSac mauSac;
    private SanPham sanPham;
    private int soLuong;
    private double donGia;
    private String trangThai;
}