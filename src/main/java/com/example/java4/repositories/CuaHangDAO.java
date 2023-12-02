package com.example.java4.repositories;

import com.example.java4.model.CuaHang;
import com.example.java4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CuaHangDAO {
//    SessionFactory factory = HibernateUtil.getFACTORY();
    public void addCuaHang(String id, String ma, String ten, String diaChi, String thanhPho, String quocGia){
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory =cfg.buildSessionFactory();
        Session session  = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            CuaHang cuaHang = new CuaHang(id, ma, ten,diaChi, thanhPho,quocGia);
            session.save(cuaHang);
            tx.commit();
            System.out.println("Add success");
          //  Block of code to try

        }
        catch(Exception e) {
          //  Block of code to handle errors
            e.printStackTrace();
        }
    }
    public ArrayList<CuaHang> getLstCuaHang(){
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory =cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<CuaHang> lstCuaHang = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            lstCuaHang = (ArrayList<CuaHang>) session.createQuery("From CuaHang").list();
            tx.commit();
            System.out.println(lstCuaHang);
          //  Block of code to try
        }

        catch(Exception e) {
          //  Block of code to handle errors
            e.printStackTrace();
        }
        return lstCuaHang;
    }

    public CuaHang getByID(String id){
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory =cfg.buildSessionFactory();
         Session session = factory.openSession();
         Transaction tx = null;
         CuaHang cuaHang = new CuaHang();
         try {
             tx = session.beginTransaction();
             cuaHang = session.get(CuaHang.class,id);
             System.out.println(cuaHang.toString());
         }
         catch(Exception e) {
             e.printStackTrace();
         }
         return cuaHang;
    }

    public void delete(CuaHang cuaHang) {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory =cfg.buildSessionFactory();
        Transaction tx = null;
        try {
            Session session = factory.openSession();
            tx = session.beginTransaction();
            session.delete(cuaHang);
            System.out.println("Delete sucess!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
