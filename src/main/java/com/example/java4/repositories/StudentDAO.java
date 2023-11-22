package com.example.java4.repositories;

import com.example.java4.model.Clss;
import com.example.java4.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class StudentDAO {
    private static SessionFactory factory;
    public StudentDAO() {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    }
    public static void  addStudent(Integer id, String name, String addr, String gender, Clss clss, String height, String weight) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Clss clss1 = new Clss(null,"clss1");
            Student student1 = new Student(null,"John Doe", "123 Main St", "Male",clss1 , "175 cm", "70 kg");
            session.save(student1);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}