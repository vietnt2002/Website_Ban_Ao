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
     @Column(name="id", unique = true, nullable = false)
     @GeneratedValue(generator = "UUID")
     @GenericGenerator(
             name = "UUID",
             strategy = "org.hibernate.id.UUIDGenerator"
     )
     private String id;
     @Column(name="ma")
     private String ma;
     @Column(name="ten")
     private String ten;
}
