package com.example.java4.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {
    private String id;
    private NhanVien nv;
    private KhachHang kh;
    private Date ngayMua;
}