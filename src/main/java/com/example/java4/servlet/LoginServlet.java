package com.example.java4.servlet;


import com.example.java4.Singleton.HoaDonSingleton;
import com.example.java4.Singleton.KhachHangSingleton;
import com.example.java4.model.*;
import com.example.java4.service.HoaDonService;
import com.example.java4.service.KhachHangService;
import com.example.java4.service.SanPhamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet( value={"/login","/enter","/return"})
public class LoginServlet extends HttpServlet {
    ArrayList<KhachHang> lstKhachHangs = new ArrayList<>();
    ArrayList<SanPham> lstSanPhams = new ArrayList<>();
    ArrayList<ChiTietSP> lstChiTietSPS  = new ArrayList<>();
    KhachHangService khachHangService = new KhachHangService();
    SanPhamService sanPhamService = new SanPhamService();
    HoaDonService hoaDonService  = new HoaDonService();
    String idHookup = null;
    public void reload(){
        lstKhachHangs = khachHangService.getAll();
    }
    public void reloadSanPham(){
           lstSanPhams = sanPhamService.getAllSP();
    }
    public void reloadChiTietSP(){
         lstChiTietSPS = sanPhamService.getAllChiTietSP();
    }
    public boolean checkingPort(String userName,String pass){
        reload();
        boolean stt = false;
        for (KhachHang kh : lstKhachHangs) {
             if(kh.getSdt().equals(userName)&&kh.getMatKhau().equals(pass)){
                 idHookup = kh.getIdKhachHang();
                 stt = true;
                 KhachHangSingleton.getInstance().khachHang = kh;
                 break;
             }
             else{
                 stt = false;
             }
        }
        return stt;
    }
    public HoaDon startTraceHoaDon(KhachHang kh){
        return HoaDonSingleton.getInstance().hoaDon = hoaDonService.getHoaDonAvai(kh);
    }
    public HoaDon createNewHoaDon(KhachHang kh){
        java.sql.Date sqlDateNow = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        int count = hoaDonService.getAllHoaDon().size();
        return hoaDonService.add(null,kh,null,count+1,sqlDateNow, null, null, null, 0,kh.getTen(),kh.getDiaChi(),kh.getSdt());
    }
    public void init(){
        System.out.println("init success");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if(uri.contains("/login")){
            request.getRequestDispatcher("views/Login.jsp").forward(request,response);
        }
        if(uri.contains("/enter")){
             String userName = request.getParameter("userNameInp");
             String pass = request.getParameter("passInp");
             if(checkingPort(userName,pass)==true){
                 KhachHang khachHang = KhachHangSingleton.getInstance().khachHang;
                 if(startTraceHoaDon(khachHang)==null){
                     createNewHoaDon(KhachHangSingleton.getInstance().khachHang);
                     System.out.println(startTraceHoaDon(KhachHangSingleton.getInstance().khachHang));
                 }
                 reloadChiTietSP();
                 request.setAttribute("khachHang",khachHang);
                 request.setAttribute("lstChiTietSanPham",lstChiTietSPS);
                 request.getRequestDispatcher("views/Shoping.jsp").forward(request,response);
             }
        }
        if(uri.contains("/return")){
             response.sendRedirect("/Home");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
    }
    public void destroy() {
        System.out.println("do destroy!");
    }
}

