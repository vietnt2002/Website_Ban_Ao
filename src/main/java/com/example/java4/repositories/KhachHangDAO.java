package com.example.java4.repositories;

import com.example.java4.model.KhachHang;
import com.example.java4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;

public class KhachHangDAO {
    //     SessionFactory factory = HibernateUtil.getFACTORY();
    Transaction tx = null;

    public void addKhachHang(String id, String ma, String ten, String tenDem, String ho, Date ngaySinh, String sdt, String diaChi, String thanhPho, String quocGia, String matKhau) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        tx = session.beginTransaction();
        KhachHang kh = new KhachHang(id, ma, ten, tenDem, ho, ngaySinh, sdt, diaChi, thanhPho, quocGia, matKhau);
        session.save(kh);
        tx.commit();
        System.out.println("Add customer success");
    }

    public ArrayList<KhachHang> getLstKhachHang() {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        ArrayList<KhachHang> lstKH = new ArrayList<>();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            lstKH = (ArrayList<KhachHang>) session.createQuery("from KhachHang").list();
            tx.commit();
            System.out.println(lstKH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstKH;
    }

    public KhachHang getById(String id) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory =cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        KhachHang kh = new KhachHang();
        try {
            kh = session.get(KhachHang.class, id);
            System.out.println(kh.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kh;
    }

}
