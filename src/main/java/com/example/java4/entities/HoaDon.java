package com.example.java4.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="HoaDon")
public class HoaDon {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name="IdKH")
    private Integer idKH;
    @Column(name="IdNV")
    private Integer idNV;
    @Column(name="NgayMuaHang")
    private Date ngayMuaHang;
    @Column(name = "TrangThai")
    private Integer trangThai;
}