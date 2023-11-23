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
    private String id;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;

    public DongSP() {
    }

    public DongSP(String id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }

}
