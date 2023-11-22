package com.example.java4.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
    @Entity
    @Table(name = "clss")
    public class Clss implements Serializable {
        @Id
        @Column(name = "idClss", unique = true, nullable = false)
        @GeneratedValue
        private Integer id;
        @Column(name = "tenLop", length = 20)
        private String name;

    public Clss() {
    }

    public Clss(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String clssName) {
        this.name = clssName;
    }

    @Override
    public String toString() {
        return "Clss{" +
                "id=" + id +
                ", clssName='" + name + '\'' +
                '}';
    }
}
