package com.example.java4;

import com.example.java4.model.*;
import com.example.java4.repositories.ChucVuDAO;
import com.example.java4.repositories.ClssDAO;
import com.example.java4.repositories.CuaHangDAO;
import com.example.java4.repositories.StudentDAO;

import java.sql.Date;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
//        String id, String ma, String ten, String diaChi, String thanhPho, String quocGia
        CuaHang cuaHang = new CuaHang(null,"VN01","AppleStore","HoanKiem","Hanoi","Vietnam");
        CuaHangDAO cuaHangDAO = new CuaHangDAO();
        cuaHangDAO.getLstCuaHang();
    }
}
