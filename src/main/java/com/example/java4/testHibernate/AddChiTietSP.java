package com.example.java4.testHibernate;
import com.example.java4.model.*;
import com.example.java4.repositories.*;

public class AddChiTietSP {
    public static void main(String[] args) {
        SanPhamDAO spDao = new SanPhamDAO();
        NSXDAO nsxdao = new NSXDAO();
        MauSacDAO mauSacDAO = new MauSacDAO();
        DongSPDAO dongSPDAO = new DongSPDAO();
        DongSP dongSP =  dongSPDAO.getById("F4EDEC56-C16C-40CF-94B9-CF4C400F2DAB");
        NSX nsx = nsxdao.getById("8E506B4A-C19D-4185-AF93-151470538CC7");
        SanPham sp =  spDao.getById("4685EFA6-188E-4831-AF5F-483FDF4447C8");
        MauSac mauSac = mauSacDAO.getById("6F223CFF-C909-44A7-A408-A85E4303E0D7");
//       String id, SanPham sp, NSX nsx, String idMauSac, DongSP dongSP, int namBH, String mota, int soLuongTon, double giaNhap, double giaBan
        ChiTietSP chiTietSP = new ChiTietSP(null,sp,nsx,mauSac,dongSP,2023,"The best deal", 15, 900,1200,"");
        ChiTietSPDAO  chiTietSPDAO = new ChiTietSPDAO();
        chiTietSPDAO.addChiTietSP(null,sp,nsx,mauSac,dongSP,2023,"The best deal", 15, 900,1200,"");
    }
}
