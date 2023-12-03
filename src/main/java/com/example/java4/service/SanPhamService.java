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

    public void addSP(String id, String ma, String ten,String link) {
         sanPhamDAO.add(id,ma,ten,link);
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
    public void addChiTietSP(String idChiTietSP, SanPham sp, NSX nsx, MauSac mauSac, DongSP dongSP, int namBH, String mota, int soLuongTon, double giaNhap, double giaBan,String link){
         chiTietSPDAO.addChiTietSP(idChiTietSP,sp,nsx,mauSac,dongSP,namBH,mota,soLuongTon,giaNhap,giaBan, link);
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

    public NSX getByIDNSX(String idNSX) {
        return  nsxdao.getById(idNSX);
    }

    public MauSac getByIdMauSac(String idMauSac) {
         return mauSacDAO.getById(idMauSac);
    }

    public DongSP getByIDDongSP(String idDongSP) {
         return dongSPDAO.getById(idDongSP);
    }

    public void deleteDongSP(DongSP dongSP) {
        dongSPDAO.delete(dongSP);
    }

    public void addDongSP(String id, String ma, String ten) {
        dongSPDAO.addDongSP(id,ma,ten);
    }

    public void updateDongSP(String id, String ma, String ten) {
         dongSPDAO.update(id,ma,ten);
    }

    public void deleteMauSac(MauSac mauSac) {
        mauSacDAO.delete(mauSac);
    }

    public void addMauSac(String id, String ma, String ten) {
        mauSacDAO.addMauSac(id,ma,ten);
    }

    public void updateMauSac(String id, String ma, String ten) {
        mauSacDAO.update(id,ma,ten);
    }

    public void deleteNsx(NSX nsx) {
        nsxdao.delete(nsx);
    }

    public void addNsx(String id, String ma, String ten) {
        nsxdao.add(id,ma,ten);
    }

    public void updateNsx(String id, String ma, String ten) {
        nsxdao.update(id,ma,ten);
    }
}
