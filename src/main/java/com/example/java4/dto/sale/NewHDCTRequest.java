package com.example.java4.dto.sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewHDCTRequest {
    private String id;
    private String idHD;
    private String idSPCT;
    private int soLuong;
    private double donGia;
    private String trangThai;
}