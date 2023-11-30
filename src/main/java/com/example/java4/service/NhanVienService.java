package com.example.java4.service;

import com.example.java4.model.ChucVu;
import com.example.java4.model.CuaHang;
import com.example.java4.model.NhanVien;
import com.example.java4.repositories.ChucVuDAO;
import com.example.java4.repositories.CuaHangDAO;
import com.example.java4.repositories.NhanVienDAO;

import java.sql.Date;
import java.util.ArrayList;

public class NhanVienService {
    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    ChucVuDAO chucVuDAO = new ChucVuDAO();
    CuaHangDAO cuaHangDAO = new CuaHangDAO();

    //// Nhan Vien
    public ArrayList<NhanVien> getAllNhanVien() {
        return nhanVienDAO.getAll();
    }

    public void addNhanVien(String idNV, String ma, String ten, String temDem, String ho, String gioiTinh, Date ngaySinh, String diaChi, String sdt, String matKhau, CuaHang ch, ChucVu cv, int trangThai) {
        nhanVienDAO.add(idNV, ma, ten, temDem, ho, gioiTinh, ngaySinh, diaChi, sdt, matKhau, ch, cv, trangThai);
    }

    public NhanVien getByIDNhanVien(String id) {
        return nhanVienDAO.getByID(id);
    }

    public void deleteNhanvienByID(NhanVien nv) {
        nhanVienDAO.deleteNhanVienByID(nv);
    }

    //////Chuc vu
    public ArrayList<ChucVu> getAllChucVu() {
        return chucVuDAO.getLstChucVu();
    }

    public void addChucVu(String id, String ma, String ten) {
        chucVuDAO.addChucVu(id, ma, ten);
    }

    public ChucVu getByID(String id) {
        return chucVuDAO.getByID(id);
    }

    public void deleteChucVu(ChucVu chucVu) {
        chucVuDAO.deleteChucVu(chucVu);
    }

    ////Cua hang
    public ArrayList<CuaHang> getAllCuaHang() {
        return cuaHangDAO.getLstCuaHang();
    }

    public void addCuaHang(String id, String ma, String ten, String diaChi, String thanhPho, String quocGia) {
        cuaHangDAO.addCuaHang(id, ma, ten, diaChi, thanhPho, quocGia);
    }

    public CuaHang getByIDCuaHang(String id) {
        return cuaHangDAO.getByID(id);
    }

    public void deleteCuaHang(CuaHang cuaHang) {
        cuaHangDAO.delete(cuaHang);
    }
}
