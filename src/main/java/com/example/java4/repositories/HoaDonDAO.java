package com.example.java4.repositories;

import com.example.java4.model.HoaDon;
import com.example.java4.model.KhachHang;
import com.example.java4.model.NhanVien;
import com.example.java4.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;

public class HoaDonDAO {
//    SessionFactory factory = HibernateUtil.getFACTORY();
    Transaction tx = null;

    public void add(String idHoaDon, KhachHang KH, NhanVien nv, String ma, Date ngayTao, Date ngayThanhToan, Date ngayShip, Date ngayNhan, int tinhTrang, String tenNguoiNhan, String diaChi, String sdt){
        HoaDon hoaDon = new HoaDon(idHoaDon,KH,nv,ma,ngayTao,ngayThanhToan,ngayShip,ngayNhan,tinhTrang,tenNguoiNhan,diaChi,sdt);
        try {
            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory =cfg.buildSessionFactory();
            Session session  = factory.openSession();
            tx  = session.beginTransaction();
            session.save(hoaDon);
            tx.commit();
            System.out.println("add succes!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HoaDon> getAll(){
        ArrayList<HoaDon> lstHoaDons = new ArrayList<>();
        try {
            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory =cfg.buildSessionFactory();
            Session session = factory.openSession();
            tx = session.beginTransaction();
            lstHoaDons = (ArrayList<HoaDon>) session.createQuery("from HoaDon").list();
            System.out.println(lstHoaDons);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return  lstHoaDons;
    }

    public HoaDon getByID(String id){
         HoaDon hoaDon = new HoaDon();
         try {
             org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
             cfg.configure("hibernate.cfg.xml");
             SessionFactory factory =cfg.buildSessionFactory();
             Session session = factory.openSession();
             tx = session.beginTransaction();
             hoaDon = session.get(HoaDon.class,id);
             System.out.println(hoaDon.toString());
         }
         catch(Exception e) {
             e.printStackTrace();
         }
         return  hoaDon;
    }
}
