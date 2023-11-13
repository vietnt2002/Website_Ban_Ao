package com.example.java4.model;

import java.sql.Date;

public class Employee {
    private String id;
    private String idStaff;
    private String fullName;
    private Date dob;
    private String gender;
    private Position position;
    private String idCardNo;
    private Date idCardDate;
    private Province Province;
    private String placeOfResidence;
    private String phoneNumber;
    private String email;

    public Employee() {
    }

    public Employee(String id, String idStaff, String fullName, Date dob, String gender, Position position, String idCardNo, Date idCardDate, com.example.java4.model.Province province, String placeOfResidence, String phoneNumber, String email) {
        this.id = id;
        this.idStaff = idStaff;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.position = position;
        this.idCardNo = idCardNo;
        this.idCardDate = idCardDate;
        Province = province;
        this.placeOfResidence = placeOfResidence;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Date getIdCardDate() {
        return idCardDate;
    }

    public void setIdCardDate(Date idCardDate) {
        this.idCardDate = idCardDate;
    }

    public com.example.java4.model.Province getProvince() {
        return Province;
    }

    public void setProvince(com.example.java4.model.Province province) {
        Province = province;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", idStaff='" + idStaff + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", position=" + position +
                ", idCardNo='" + idCardNo + '\'' +
                ", idCardDate=" + idCardDate +
                ", Province=" + Province +
                ", placeOfResidence='" + placeOfResidence + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
