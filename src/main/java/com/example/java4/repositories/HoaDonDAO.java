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

    public HoaDon add(String idHoaDon, KhachHang KH, NhanVien nv,int ma, Date ngayTao, Date ngayThanhToan, Date ngayShip, Date ngayNhan, int tinhTrang, String tenNguoiNhan, String diaChi, String sdt){
        HoaDon hoaDon = new HoaDon(idHoaDon,KH,nv,ma,ngayTao,ngayThanhToan,ngayShip,ngayNhan,tinhTrang,tenNguoiNhan,diaChi,sdt);
        HoaDon hd = new HoaDon();
        try {
            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory =cfg.buildSessionFactory();
            Session session  = factory.openSession();
            tx  = session.beginTransaction();
            session.save(hoaDon);
            tx.commit();
            hd = (HoaDon) session.createQuery(" from HoaDon order by ma desc").setMaxResults(1).list().get(0);
            System.out.println("add succes!");
            System.out.println("test hd:"+hd);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return hd;
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

    public void delete(HoaDon hoaDon) {
        try {
            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory =cfg.buildSessionFactory();
            Session session = factory.openSession();
            tx = session.beginTransaction();
            session.delete(hoaDon);
            tx.commit();
            System.out.println("delete success!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public HoaDon getHoaDonAvai(KhachHang kh) {
        HoaDon hoaDon = null;
        try {
            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory factory = cfg.buildSessionFactory();
            Session session = factory.openSession();
            tx = session.beginTransaction();
            String idkh = kh.getIdKhachHang();
            hoaDon = (HoaDon) session.createQuery("from HoaDon hd where hd.tinhTrang= 0 and hd.KH.idKhachHang=:idkh").setParameter("idkh",idkh).list().get(0);
            tx.commit();
            System.out.println("hoaDon available"+hoaDon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

}
