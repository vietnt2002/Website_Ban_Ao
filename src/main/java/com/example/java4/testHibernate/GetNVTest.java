package com.example.java4.testHibernate;

import com.example.java4.repositories.NhanVienDAO;

public class GetNVTest {
    public static void main(String[] args) {
        NhanVienDAO dao = new NhanVienDAO();
        dao.getAll();
        System.out.println("get by id");
        dao.getAll();
    }
}
