package com.example.java4.repositories;

import com.example.java4.model.ChucVu;
import com.example.java4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ChucVuDAO {
    private SessionFactory factory = HibernateUtil.getFACTORY();
    public void addChucVu(String id, String ma, String ten){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
          //  Block of code to try
            tx = session.beginTransaction();
            ChucVu chucVu = new ChucVu(null,ma,ten);
            session.save(chucVu);
            tx.commit();
            System.out.println("add success!");
        }
        catch(Exception e) {
          e.printStackTrace();
        }
    }
    public ArrayList<ChucVu> getLstChucVu(){
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<ChucVu> lstChucVus = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            lstChucVus = (ArrayList<ChucVu>) session.createQuery("from ChucVu").list();
            tx.commit();
            System.out.println(lstChucVus);
        }
        catch(Exception e) {
          //  Block of code to handle errors
            e.printStackTrace();
        }
        return lstChucVus;
    }

    public ChucVu getByID(String id){
        Session session = factory.openSession();
        Transaction tx = null;
        ChucVu  chucVu = new ChucVu();
        try {
            chucVu  = session.get(ChucVu.class,id);
            System.out.println(chucVu.toString());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return  chucVu;
    }
}
