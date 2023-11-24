package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "ChucVu")
public class ChucVu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idChucVu;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;

    public ChucVu() {
    }

    public ChucVu(String idChucVu, String ma, String ten) {
        this.idChucVu = idChucVu;
        this.ma = ma;
        this.ten = ten;
    }

    public String getIdChucVu() {
        return idChucVu;
    }

    public void setIdChucVu(String idChucVu) {
        this.idChucVu = idChucVu;
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

    @Override
    public String toString() {
        return "ChucVu{" +
                "idChucVu='" + idChucVu + '\'' +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }
}
