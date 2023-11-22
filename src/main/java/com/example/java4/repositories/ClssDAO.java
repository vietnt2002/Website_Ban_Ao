package com.example.java4.repositories;

import com.example.java4.model.Clss;
import com.example.java4.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ClssDAO {
    private static SessionFactory factory;
    public ClssDAO() {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }
    public static void  addClss(Integer id, String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Clss clss1 = new Clss(id,name);
            session.save(clss1);
            ArrayList<Clss> lstClss = (ArrayList<Clss>) session.createQuery("from Clss ").list();
            tx.commit();
            System.out.println(lstClss.toString());
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}