package com.example.java4.testHibernate;

import com.example.java4.model.*;
import com.example.java4.repositories.ChiTietSPDAO;
import com.example.java4.repositories.HoaDonChiTietDAO;
import com.example.java4.repositories.HoaDonDAO;
import com.example.java4.repositories.SanPhamDAO;

import java.sql.Date;

public class AddHoaDonChiTietTest {
    public static void main(String[] args) {
        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO();
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        ChiTietSPDAO chiTietSPDAO = new ChiTietSPDAO();
//      HoaDon hoaDon, SanPham sp, int soluong, double donGia
        HoaDon hoaDon = hoaDonDAO.getByID("76D0E28D-D3C2-46F8-962A-2DE3D0EAFEFF");
        ChiTietSP chiTietSP = chiTietSPDAO.getByID("226D0A2B-20C8-4580-9EF2-7CA062E1629A");
        hoaDonChiTietDAO.add(hoaDon,chiTietSP,1,1000.5);
    }
}
