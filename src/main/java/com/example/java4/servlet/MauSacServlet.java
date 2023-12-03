package com.example.java4.servlet;

import com.example.java4.model.DongSP;
import com.example.java4.model.MauSac;
import com.example.java4.service.SanPhamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

//,"/add","/delete","/detail"
@WebServlet(value = {"/mauSac","/detail-mauSac","/routeUpdate-mauSac","/addMauSac","/delete-mauSac","/update-mauSac"})
public class MauSacServlet extends HttpServlet {
    public ArrayList<MauSac> lstMauSacs = new ArrayList<>();
    SanPhamService service = new SanPhamService();

    public void reload() {
        lstMauSacs = service.getAllMauSac();
    }

    public void init() {
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if (uri.contains("/mauSac")) {
            reload();
            request.setAttribute("lstMauSac", lstMauSacs);
            request.getRequestDispatcher("views/MauSac.jsp").forward(request, response);
        }
        if (uri.contains("/detail-mauSac")) {
            String id = request.getParameter("id");
            MauSac mauSac = new MauSac();
            for (MauSac ms : lstMauSacs) {
                if (ms.getIdMauSac().equals(id)) {
                    mauSac = ms;
                }
            }
            request.setAttribute("mauSac", mauSac);
            request.getRequestDispatcher("views/ShowMauSac.jsp").forward(request,response);
        }
        if(uri.contains("/routeUpdate-mauSac")){
            String id = request.getParameter("id");
            MauSac mauSac = new MauSac();
            for (MauSac ms : lstMauSacs) {
                if(ms.getIdMauSac().equals(id)){
                    mauSac = ms;
                }
            }
            request.setAttribute("mauSac",mauSac);
            request.getRequestDispatcher("views/ShowMauSac.jsp").forward(request,response);
        }
        if(uri.contains("/delete-mauSac")){
            String id = request.getParameter("id");
            MauSac mauSac = new MauSac();
            for (MauSac ms : lstMauSacs) {
                if (ms.getIdMauSac().equals(id)) {
                    mauSac = ms;
                }
            }
            service.deleteMauSac(mauSac);
            response.sendRedirect("/Home/mauSac");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
        String uri = request.getRequestURI();
        if(uri.contains("/addMauSac")){
            String id = request.getParameter("idInp");
            String ma = request.getParameter("maInp");
            String ten = request.getParameter("tenInp");
            service.addMauSac(id,ma,ten);
            response.sendRedirect("/Home/mauSac");
        }
        if(uri.contains("/update-mauSac")){
            String id = request.getParameter( "idInp");
            String ma = request.getParameter("maInp");
            String ten = request.getParameter("tenInp");
            service.updateMauSac(id,ma,ten);
            response.sendRedirect("/Home/mauSac");
        }
    }

    public void destroy() {
        System.out.println("do destroy!");
    }

}

