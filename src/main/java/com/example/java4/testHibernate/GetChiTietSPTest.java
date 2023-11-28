package com.example.java4.testHibernate;

import com.example.java4.repositories.ChiTietSPDAO;
import com.example.java4.repositories.GioHangChiTietDAO;

public class GetChiTietSPTest {
    public static void main(String[] args) {
        ChiTietSPDAO dao = new ChiTietSPDAO();
        dao.getByID("226D0A2B-20C8-4580-9EF2-7CA062E1629A");
    }
}
