package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Data
@Entity
@Table(name = "NSX")
public class NSX implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     private String idNSX;
     @Column(name="ma")
     private String ma;
     @Column(name="ten")
     private String ten;

     public NSX() {
     }

     public NSX(String idNSX, String ma, String ten) {
          this.idNSX = idNSX;
          this.ma = ma;
          this.ten = ten;
     }

     public String getIdNSX() {
          return idNSX;
     }

     public void setIdNSX(String idNSX) {
          this.idNSX = idNSX;
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
          return "NSX{" +
                  "idNSX='" + idNSX + '\'' +
                  ", ma='" + ma + '\'' +
                  ", ten='" + ten + '\'' +
                  '}';
     }
}
