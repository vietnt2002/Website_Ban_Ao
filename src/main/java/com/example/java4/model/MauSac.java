package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "MauSac")
public class MauSac implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idMauSac;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "ten")
    private String ten;

    public MauSac() {
    }

    public MauSac(String idMauSac, String ma, String ten) {
        this.idMauSac = idMauSac;
        this.ma = ma;
        this.ten = ten;
    }

    public String getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(String idMauSac) {
        this.idMauSac = idMauSac;
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
        return "MauSac{" +
                "idMauSac='" + idMauSac + '\'' +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }
}

