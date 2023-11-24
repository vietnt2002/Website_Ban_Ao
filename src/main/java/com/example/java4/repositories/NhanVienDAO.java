package com.example.java4.repositories;

import com.example.java4.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class NhanVienDAO {
    SessionFactory  factory = HibernateUtil.getFACTORY();
    Transaction  tx = null;
    public void add(){

    }

}
