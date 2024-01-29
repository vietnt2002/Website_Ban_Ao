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
@Table(name="NhanVien")
public class NhanVien {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="Ten")
    private String ten;
    @Column(name ="Ma")
    private String ma;
    @Column(name ="TenDangNhap")
    private String tenDangNhap;
    @Column(name ="MatKhau")
    private String matKhau;
    @Column(name ="TrangThai")
    private Integer trangThai;
}