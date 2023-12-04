//package com.example.java4.repositories;
//
//import com.example.java4.model.GioHang;
//import com.example.java4.model.KhachHang;
//import com.example.java4.model.NhanVien;
//import com.example.java4.util.HibernateUtil;
//import org.hibernate.Hibernate;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import java.sql.Date;
//import java.util.ArrayList;
//
//public class GioHangDAO {
////    SessionFactory factory = HibernateUtil.getFACTORY();
//    Transaction tx = null;
//
//    public void add(String idGioHang, KhachHang kh, NhanVien idNV, String ma, Date ngayTao, Date ngayThanhToan, String tenNguoiNhan, String diaChi, String sdt, int tinhTrang){
//        try {
//            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
//            cfg.configure("hibernate.cfg.xml");
//            SessionFactory factory =cfg.buildSessionFactory();
//            Session session = factory.openSession();
//            tx = session.beginTransaction();
//            GioHang gioHang = new GioHang(idGioHang,kh,idNV,ma,ngayTao,ngayThanhToan,tenNguoiNhan,diaChi,sdt,tinhTrang);
//            session.save(gioHang);
//            tx.commit();
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<GioHang> getLst(){
//        ArrayList<GioHang> lstGioHangs = new ArrayList<>();
//        try {
//            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
//            cfg.configure("hibernate.cfg.xml");
//            SessionFactory factory =cfg.buildSessionFactory();
//            Session session = factory.openSession();
//            tx = session.beginTransaction();
//            lstGioHangs = (ArrayList<GioHang>) session.createQuery("from GioHang").list();
//            System.out.println(lstGioHangs);
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//        return lstGioHangs;
//    }
//
//    public GioHang getByID(String id){
//         GioHang gioHang = new GioHang();
//         try {
//             org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
//             cfg.configure("hibernate.cfg.xml");
//             SessionFactory factory =cfg.buildSessionFactory();
//             Session session = factory.openSession();
//             tx = session.beginTransaction();
//             gioHang = session.get(GioHang.class,id);
//             System.out.println(gioHang.toString());
//         }
//         catch(Exception e) {
//             e.printStackTrace();
//         }
//         return gioHang;
//    }
//}
