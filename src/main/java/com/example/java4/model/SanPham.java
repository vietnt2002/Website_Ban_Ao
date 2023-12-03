package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "SanPham")
public class SanPham implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idSanPham;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;
    @Column(name ="Link")
    private String link;

    public SanPham() {
    }

    public SanPham(String idSanPham, String ma, String ten, String link) {
        this.idSanPham = idSanPham;
        this.ma = ma;
        this.ten = ten;
        this.link = link;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "idSanPham='" + idSanPham + '\'' +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
