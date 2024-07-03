package com.example.java4.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HoaDonResponse {

    private String idHD;
    private String maHD;
    private String tenSP;
    private Integer soLuong;
    private String hinhAnh1;
    private String tenMauSac;
    private String tenKieuTay;
    private BigDecimal giaBan;
}
