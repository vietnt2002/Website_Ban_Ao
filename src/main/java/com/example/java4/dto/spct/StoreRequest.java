package com.example.java4.dto.spct;
import com.example.java4.model.KichThuoc;
import com.example.java4.model.MauSac;
import com.example.java4.model.SanPham;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    @NotBlank(message = "Do not empty id")
    private String id;
    @NotBlank(message = "Do not empty ma SPCT")
    private String maSPCT;
    private String kichThuoc;
    private String mauSac;
    private String sanPham;
    private int soLuong;
    private double donGia;
    @NotBlank(message = "Do not empty trang thai")
    private String trangThai;
}