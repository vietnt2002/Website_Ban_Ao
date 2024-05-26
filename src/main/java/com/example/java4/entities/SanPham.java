package com.example.java4.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SanPham")

public class SanPham {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "Id")
    private UUID id;

    @Column(name = "Ma")
    @org.hibernate.annotations.Generated(value = GenerationTime.ALWAYS)
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "indx")
    private Integer indx;

    @Column(name = "TrangThai")
    private String trangThai;
}