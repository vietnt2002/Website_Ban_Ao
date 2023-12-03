package com.example.java4.repositories;

import com.example.java4.model.KhachHang;
import com.example.java4.model.MauSac;
import com.example.java4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MauSacDAO {
    //    SessionFactory factory = HibernateUtil.getFACTORY();
    Transaction tx = null;

    public void addMauSac(String id, String ma, String ten) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        tx = session.beginTransaction();
        MauSac mauSac = new MauSac(id, ma, ten);
        try {
            session.save(mauSac);
            tx.commit();
            System.out.println("Add success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MauSac> getLstMauSac() {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        ArrayList<MauSac> lstMauSac = new ArrayList<>();
        Session session = factory.openSession();
        tx = session.beginTransaction();
        try {
            lstMauSac = (ArrayList<MauSac>) session.createQuery("from MauSac").list();
            System.out.println(lstMauSac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMauSac;
    }

    public MauSac getById(String id) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        MauSac mauSac = new MauSac();
        Session session = factory.openSession();
        tx = session.beginTransaction();
        try {
            mauSac = session.get(MauSac.class, id);
            System.out.println(mauSac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSac;
    }

    public void delete(MauSac mauSac) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        tx = session.beginTransaction();
        try {
            session.delete(mauSac);
            tx.commit();
            System.out.println("delete success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String id, String ma, String ten) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        tx = session.beginTransaction();
        MauSac mauSac = new MauSac(id,ma,ten);
        try {
            session.update(mauSac);
            tx.commit();
            System.out.println("update sucess");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
