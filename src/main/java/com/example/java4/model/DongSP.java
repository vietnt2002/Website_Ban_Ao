package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "DongSP")
public class DongSP implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idDongSP;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;

    public DongSP() {
    }

    public DongSP(String idDongSP, String ma, String ten) {
        this.idDongSP = idDongSP;
        this.ma = ma;
        this.ten = ten;
    }

    public String getIdDongSP() {
        return idDongSP;
    }

    public void setIdDongSP(String idDongSP) {
        this.idDongSP = idDongSP;
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
        return "DongSP{" +
                "idDongSP='" + idDongSP + '\'' +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }
}
