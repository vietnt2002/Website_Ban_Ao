package com.example.java4.model;
import java.io.Serializable;

import jakarta.mail.FetchProfile;
import jakarta.persistence.*;

@Entity
@Table(name = "studnt")
public  class Student implements Serializable{
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue
    private Integer id;
    @Column(name = "ten", length = 20)
    private String name;
    @Column(name = "diaChi", length = 20)
    private String addr;
    @Column(name = "gioiTinh")
    private String gender;
    @ManyToOne
    @JoinColumn(name = "idClss")
    private Clss clss;

    @Column(name = "chieuCao")
    private String height;

    @Column(name = "canNang")
    private String weight;

    public Student() {
        // TODO Auto-generated constructor stub
    }

    public Student(Integer id, String name, String addr, String gender, Clss clss, String height, String weight) {
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.gender = gender;
        this.clss = clss;
        this.height = height;
        this.weight = weight;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Clss getClss() {
        return clss;
    }

    public void setClss(Clss clss) {
        this.clss = clss;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", gender='" + gender + '\'' +
                ", clss=" + clss +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
