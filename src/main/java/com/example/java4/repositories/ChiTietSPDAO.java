package com.example.java4.repositories;

import com.example.java4.model.ChiTietSP;
import com.example.java4.model.DongSP;
import com.example.java4.model.NSX;
import com.example.java4.model.SanPham;
import com.example.java4.util.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenericGenerator;

public class ChiTietSPDAO {
    private SessionFactory factory = HibernateUtil.getFACTORY();

    public void addChiTietSP(String id, SanPham sp, NSX nsx, String idMauSac, DongSP dongSP, int namBH, String mota, int soLuongTon, double giaNhap, double giaBan) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
        } catch (Exception e) {
            //  Block of code to handle errors
        }
    }

}
