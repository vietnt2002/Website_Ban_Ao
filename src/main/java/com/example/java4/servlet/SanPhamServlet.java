package com.example.java4.servlet;


import com.example.java4.model.SanPham;
import com.example.java4.service.SanPhamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

//"
@WebServlet(value = {"/sanPham", "/addSanPham", "/update-SanPham", "/delete-SanPham","/detail-SanPham","/routeUpdate-SanPham"})
public class SanPhamServlet extends HttpServlet {
    SanPhamService service = new SanPhamService();
    ArrayList<SanPham> lstSanPham = new ArrayList<>();

    public void reload() {
        lstSanPham = service.getAllSP();
    }

    public void init() {
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if (uri.contains("/sanPham")) {
            reload();
            request.setAttribute("lstSanPham", lstSanPham);
            request.getRequestDispatcher("views/SanPham.jsp").forward(request, response);
        }
        if(uri.contains("/detail-SanPham")){
            String id = request.getParameter("id");
            SanPham sanPham = new SanPham();
            sanPham = service.getByIDSP(id);
            request.setAttribute("sanPham",sanPham);
            request.getRequestDispatcher("views/DetailSanPham.jsp").forward(request,response);
        }
        if(uri.contains("/routeUpdate-SanPham")){
            String id = request.getParameter("id");
            SanPham sanPham = new SanPham();
            sanPham = service.getByIDSP(id);
            request.setAttribute("sanPham",sanPham);
            request.getRequestDispatcher("views/DetailSanPham.jsp").forward(request,response);
        }
        if (uri.contains("/update-SanPham")) {
            String id = request.getParameter("idInp");
            System.out.println(id);
            String ma = request.getParameter("maInp");
            String ten = request.getParameter("tenInp");
            String link = request.getParameter("linkInp");
            SanPham sanPham = new SanPham(id,ma,ten,link);
            service.updateSP(sanPham);
            response.sendRedirect("/Home/sanPham");
        }
        if (uri.contains("/delete-SanPham")) {
            String id = request.getParameter("id");
            SanPham sanPham = new SanPham();
            sanPham = service.getByIDSP(id);
            service.deleteSP(sanPham);
            response.sendRedirect("/Home/sanPham");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
        String uri = request.getRequestURI();
        if (uri.contains("/addSanPham")) {
            String id = null;
            String ma = request.getParameter("maInp");
            String ten = request.getParameter("tenInp");
            String link = request.getParameter("linkInp");
            service.addSP(id, ma, ten, link);
        }

        response.sendRedirect("/Home/sanPham");
    }

    public void destroy() {
        System.out.println("do destroy!");
    }

}
