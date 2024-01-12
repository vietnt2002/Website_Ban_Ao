package com.example.java4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {
    private String id;
    private String ten;
    private String sdt;
    private String maKH;
    private String trangThai;
}