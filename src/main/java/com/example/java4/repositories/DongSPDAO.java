package com.example.java4.repositories;

import com.example.java4.model.DongSP;
import com.example.java4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class DongSPDAO {
    SessionFactory factory = HibernateUtil.getFACTORY();

    public void addDongSP(String id, String ma, String ten) {
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

    public ArrayList<DongSP> getLstDongSP(){
        ArrayList<DongSP> lstDongSP = new ArrayList<>();
        Session  session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            lstDongSP = (ArrayList<DongSP>) session.createQuery("From DongSP").list();
            tx.commit();
            System.out.println(lstDongSP);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        return lstDongSP;
    }
}
