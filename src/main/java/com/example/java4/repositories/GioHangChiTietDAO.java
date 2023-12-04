//package com.example.java4.repositories;
//
//import com.example.java4.model.ChiTietSP;
//import com.example.java4.model.GioHang;
//import com.example.java4.model.GioHangChiTiet;
//import com.example.java4.util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
//
//import java.util.ArrayList;
//
//public class GioHangChiTietDAO {
////    SessionFactory factory = HibernateUtil.getFACTORY();
//    Transaction tx = null;
//
//    public void add(GioHang gioHang, ChiTietSP chiTietSP, int soLuong, double donGia, double donGiaKhiGiam) {
//        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
//        cfg.configure("hibernate.cfg.xml");
//        SessionFactory factory =cfg.buildSessionFactory();
//        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet(gioHang, chiTietSP, soLuong, donGia, donGiaKhiGiam);
//        try {
//            Session session = factory.openSession();
//            tx = session.beginTransaction();
//            session.save(gioHangChiTiet);
//            tx.commit();
//            System.out.println("add success");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<GioHangChiTiet> getAll() {
//        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
//        cfg.configure("hibernate.cfg.xml");
//        SessionFactory factory =cfg.buildSessionFactory();
//        ArrayList<GioHangChiTiet> lstGioHangChiTiets = new ArrayList<>();
//        try {
//            Session session = factory.openSession();
//            tx = session.beginTransaction();
//            lstGioHangChiTiets = (ArrayList<GioHangChiTiet>) session.createQuery("from GioHangChiTiet").list();
//            System.out.println(lstGioHangChiTiets.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return lstGioHangChiTiets;
//    }
//
//    public GioHangChiTiet getByID(String id) {
//        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
//        cfg.configure("hibernate.cfg.xml");
//        SessionFactory factory =cfg.buildSessionFactory();
//        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
//        try {
//            Session session = factory.openSession();
//            tx = session.beginTransaction();
//            gioHangChiTiet = session.get(GioHangChiTiet.class, id);
//            System.out.println(gioHangChiTiet);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return gioHangChiTiet;
//    }
//
//}
