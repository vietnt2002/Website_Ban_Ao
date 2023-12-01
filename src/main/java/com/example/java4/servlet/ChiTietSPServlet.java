package com.example.java4.servlet;
import com.example.java4.model.*;
import com.example.java4.service.SanPhamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet( value={"/chiTietSP","/addChiTietSP","/delete-chiTietSP","/detail-chiTietSP"})
public class ChiTietSPServlet extends HttpServlet {
    SanPhamService service = new SanPhamService();
    ArrayList<SanPham> lstSanPhams = new ArrayList<>();
    ArrayList<ChiTietSP> lstChiTietSPS = new ArrayList<>();
    ArrayList<MauSac> lstMauSacs  = new ArrayList<>();
    ArrayList<DongSP> lstDongSPS = new ArrayList<>();
    ArrayList<NSX> lstNSXes = new ArrayList<>();
    public void reload(){
        lstSanPhams = service.getAllSP();
        lstChiTietSPS = service.getAllChiTietSP();
        lstMauSacs = service.getAllMauSac();
        lstDongSPS = service.getAllDongSP();
        lstNSXes = service.getAllNSX();
    }
    public void init(){
        System.out.println("init success");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if(uri.contains("/chiTietSP")){
            reload();
            request.setAttribute("lstSanPham",lstSanPhams);
            request.setAttribute("lstChiTietSP",lstChiTietSPS);
            request.setAttribute("lstMauSac",lstMauSacs);
            request.setAttribute("lstDongSP",lstDongSPS);
            request.setAttribute("lstNSX", lstNSXes);
            request.getRequestDispatcher("views/ChiTietSanPham.jsp").forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
    }
    public void destroy() {
        System.out.println("do destroy!");
    }
}

