package com.example.java4.service;

import com.example.java4.model.*;
import com.example.java4.repositories.*;

import java.util.ArrayList;

public class SanPhamService {
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    ChiTietSPDAO chiTietSPDAO = new ChiTietSPDAO();
    MauSacDAO mauSacDAO = new MauSacDAO();
    DongSPDAO dongSPDAO = new DongSPDAO();
    NSXDAO nsxdao = new NSXDAO();
    public ArrayList<SanPham> getAllSP() {
         return sanPhamDAO.getLst();
    }

    public void addSP(String id, String ma, String ten) {
         sanPhamDAO.add(id,ma,ten);
    }

    public SanPham getByIDSP(String id) {
          return sanPhamDAO.getById(id);
    }

    public void updateSP(SanPham sanPham) {
        sanPhamDAO.update(sanPham);
    }

    public void deleteSP(SanPham sanPham) {
        sanPhamDAO.delete(sanPham);
    }


    public ArrayList<ChiTietSP> getAllChiTietSP(){
           return chiTietSPDAO.getLst();
    }
    public void addChiTietSP(String idChiTietSP, SanPham sp, NSX nsx, MauSac mauSac, DongSP dongSP, int namBH, String mota, int soLuongTon, double giaNhap, double giaBan){
         chiTietSPDAO.addChiTietSP(idChiTietSP,sp,nsx,mauSac,dongSP,namBH,mota,soLuongTon,giaNhap,giaBan);
    }
    public ChiTietSP  getByIDChiTietSP(String id){
         return chiTietSPDAO.getByID(id);
    }
    public void updateChiTietSP(ChiTietSP chiTietSP){
        chiTietSPDAO.update(chiTietSP);
    }
    public void deleteChiTietSP(ChiTietSP chiTietSP){
        chiTietSPDAO.delete(chiTietSP);
    }

    public ArrayList<MauSac> getAllMauSac() {
        return  mauSacDAO.getLstMauSac();
    }

    public ArrayList<DongSP> getAllDongSP() {
        return  dongSPDAO.getLstDongSP();
    }

    public ArrayList<NSX> getAllNSX() {
        return  nsxdao.getLst();
    }
}
