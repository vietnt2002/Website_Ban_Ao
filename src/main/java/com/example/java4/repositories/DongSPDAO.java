package com.example.java4.repositories;

import com.example.java4.model.DongSP;
import com.example.java4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class DongSPDAO {
//    SessionFactory factory = HibernateUtil.getFACTORY();

    public void addDongSP(String id, String ma, String ten) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        DongSP dongSp = new DongSP(id, ma, ten);
        try {
            tx = session.beginTransaction();
            session.save(dongSp);
            tx.commit();
            System.out.println("Add success");
            //  Block of code to try
        } catch (Exception e) {
            e.printStackTrace();
            //  Block of code to handle errors
        }
    }

    public ArrayList<DongSP> getLstDongSP() {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        ArrayList<DongSP> lstDongSP = new ArrayList<>();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            lstDongSP = (ArrayList<DongSP>) session.createQuery("From DongSP").list();
            tx.commit();
            System.out.println(lstDongSP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDongSP;
    }

    public DongSP getById(String id) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        DongSP dongSP = new DongSP();
        try {
            tx = session.beginTransaction();
            dongSP = session.get(DongSP.class, id);
            System.out.println(dongSP.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dongSP;
    }

    public void delete(DongSP dongSP) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(dongSP);
            tx.commit();
            System.out.println(dongSP);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String id, String ma, String ten) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        DongSP dongSP = new DongSP(id,ma,ten);
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(dongSP);
            tx.commit();
            System.out.println(dongSP);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
