package com.example.java4.service;

import com.example.java4.model.HoaDon;
import com.example.java4.model.HoaDonChiTiet;
import com.example.java4.model.KhachHang;
import com.example.java4.model.NhanVien;
import com.example.java4.repositories.HoaDonChiTietDAO;
import com.example.java4.repositories.HoaDonDAO;

import java.sql.Date;
import java.util.ArrayList;

public class HoaDonService {
    HoaDonDAO hoaDonDAO = new HoaDonDAO();
    HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO();

    public ArrayList<HoaDon> getAllHoaDon() {
         return hoaDonDAO.getAll();
    }

    public void deleteHoaDon(HoaDon hoaDon) {
         hoaDonDAO.delete(hoaDon);
    }

    public HoaDon getHoaDonAvai(KhachHang kh) {
        return hoaDonDAO.getHoaDonAvai(kh);
    }

    public HoaDon add(String idHoaDon, KhachHang KH, NhanVien nv,int ma, Date ngayTao, Date ngayThanhToan, Date ngayShip, Date ngayNhan, int tinhTrang, String tenNguoiNhan, String diaChi, String sdt) {
        return hoaDonDAO.add(idHoaDon,KH,nv,ma,ngayTao,ngayThanhToan,ngayShip,ngayNhan,tinhTrang,tenNguoiNhan,diaChi,sdt);
    }

    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTiet() {
        return hoaDonChiTietDAO.getAll();
    }

    public ArrayList<HoaDonChiTiet> getAllByIdHoaDon(String idHoaDon) {
        return  hoaDonChiTietDAO.getAllByIdHoaDon(idHoaDon);
    }
}
