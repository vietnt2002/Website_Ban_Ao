package com.example.java4.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HDCT {
    private String id;
    private HoaDon hd;
    private SPCT  spct;
    private int soLuong;
    private double donGia;
    private String trangThai;
}