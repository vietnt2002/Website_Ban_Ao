package com.example.java4.repositories;

import com.example.java4.model.ChiTietSP;
import com.example.java4.model.HoaDon;
import com.example.java4.model.HoaDonChiTiet;
import com.example.java4.model.SanPham;
import com.example.java4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class HoaDonChiTietDAO {
//    SessionFactory factory = HibernateUtil.getFACTORY();
    Transaction tx = null;

    public void add(HoaDon hoaDon, ChiTietSP chiTietSP, int soluong, double donGia) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(hoaDon,chiTietSP, soluong, donGia);
        try {
            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory =cfg.buildSessionFactory();
            Session session = factory.openSession();
            tx = session.beginTransaction();
            session.save(hoaDonChiTiet);
            tx.commit();
            System.out.println("add success !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HoaDonChiTiet> getAll() {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory =cfg.buildSessionFactory();
        ArrayList<HoaDonChiTiet> lstHoaDonChiTiets = new ArrayList<>();
        Session session = factory.openSession();
        tx = session.beginTransaction();
        try {
            lstHoaDonChiTiets = (ArrayList<HoaDonChiTiet>) session.createQuery("from HoaDon").list();
            System.out.println(lstHoaDonChiTiets);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDonChiTiets;
    }

    public HoaDonChiTiet getByID(String id) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory =cfg.buildSessionFactory();
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        try {
            Session session = factory.openSession();
            tx = session.beginTransaction();
            hoaDonChiTiet = session.get(HoaDonChiTiet.class, id);
            System.out.println(hoaDonChiTiet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDonChiTiet;
    }

}
