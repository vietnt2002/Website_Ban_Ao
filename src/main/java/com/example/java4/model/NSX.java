package com.example.java4.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

public class NSX {
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
