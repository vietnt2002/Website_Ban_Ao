package com.example.java4.repositories;

import com.example.java4.model.SanPham;
import com.example.java4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SanPhamDAO {
    SessionFactory factory = HibernateUtil.getFACTORY();
    Transaction tx = null;

    public void add(String id, String ma, String ten) {
        Session session = factory.openSession();
        tx = session.beginTransaction();
        SanPham sp = new SanPham(id, ma, ten);
        try {
            session.save(sp);
            tx.commit();
            System.out.println("add success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SanPham> getLst() {
        ArrayList<SanPham> lstSanPham = new ArrayList<>();
        Session session = factory.openSession();
        tx = session.beginTransaction();
        try {
            lstSanPham = (ArrayList<SanPham>) session.createQuery("from SanPham").list();
            System.out.println(lstSanPham);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSanPham;
    }

    public SanPham getById(String id) {
        Session session = factory.openSession();
        tx = session.beginTransaction();
        SanPham sp = new SanPham();
        try {
            sp = session.get(SanPham.class, id);
            System.out.println(sp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    public void update(SanPham sanPham) {
        try {
            Session session = factory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(sanPham);
            tx.commit();
            System.out.println("Update succes");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(SanPham sanPham){
         try {
            Session session = factory.openSession();
            tx = session.beginTransaction();
            session.delete(sanPham);
            tx.commit();
             System.out.println("Do delete success !");
         }
         catch(Exception e) {
             e.printStackTrace();
         }
    }
}
