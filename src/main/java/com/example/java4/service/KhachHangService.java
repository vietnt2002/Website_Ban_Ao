package com.example.java4.service;

import com.example.java4.model.ChucVu;
import com.example.java4.model.CuaHang;
import com.example.java4.model.KhachHang;
import com.example.java4.model.NhanVien;
import com.example.java4.repositories.ChucVuDAO;
import com.example.java4.repositories.CuaHangDAO;
import com.example.java4.repositories.KhachHangDAO;
import com.example.java4.repositories.NhanVienDAO;

import java.sql.Date;
import java.util.ArrayList;

public class KhachHangService {
    KhachHangDAO khachHangDAO = new KhachHangDAO();
    public ArrayList<KhachHang> getAll() {
        return khachHangDAO.getLstKhachHang();
    }
}
