package com.example.java4.servlet;

import com.example.java4.model.DongSP;
import com.example.java4.service.SanPhamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

//,"/add","/delete","/detail"
@WebServlet(value = {"/dongSP","/detail-dongSP","/routeUpdate-dongSP","/addDongSP","/delete-dongSP","/update-dongSP"})
public class DongSPServlet extends HttpServlet {
    public ArrayList<DongSP> lstDongSPS = new ArrayList<>();
    SanPhamService service = new SanPhamService();

    public void reload() {
        lstDongSPS = service.getAllDongSP();
    }

    public void init() {
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if (uri.contains("/dongSP")) {
            reload();
            request.setAttribute("lstDongSP", lstDongSPS);
            request.getRequestDispatcher("views/DongSP.jsp").forward(request, response);
        }
        if (uri.contains("/detail-dongSP")) {
            String id = request.getParameter("id");
            DongSP dongSP = new DongSP();
            for (DongSP dsp : lstDongSPS) {
                if (dsp.getIdDongSP().equals(id)) {
                    dongSP = dsp;
                }
            }
            request.setAttribute("dongSP", dongSP);
            request.getRequestDispatcher("views/ShowDongSP.jsp").forward(request,response);
        }
        if(uri.contains("/routeUpdate-dongSP")){
            String id = request.getParameter("id");
            DongSP dongSP = new DongSP();
            for (DongSP dsp : lstDongSPS) {
                if(dsp.getIdDongSP().equals(id)){
                    dongSP = dsp;
                }
            }
            request.setAttribute("dongSP",dongSP);
            request.getRequestDispatcher("views/ShowDongSP.jsp").forward(request,response);
        }
        if(uri.contains("/delete-dongSP")){
            String id = request.getParameter("id");
            DongSP dongSP = new DongSP();
            for (DongSP dsp : lstDongSPS) {
                if (dsp.getIdDongSP().equals(id)) {
                    dongSP = dsp;
                }
            }
            service.deleteDongSP(dongSP);
            response.sendRedirect("/Home/dongSP");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
        String uri = request.getRequestURI();
        if(uri.contains("/addDongSP")){
            String id = request.getParameter("idInp");
            String ma = request.getParameter("maInp");
            String ten = request.getParameter("tenInp");
            service.addDongSP(id,ma,ten);
            response.sendRedirect("/Home/dongSP");
        }
        if(uri.contains("/update-dongSP")){
            String id = request.getParameter( "idInp");
            String ma = request.getParameter("maInp");
            String ten = request.getParameter("tenInp");
            service.updateDongSP(id,ma,ten);
            response.sendRedirect("/Home/dongSP");
        }
    }

    public void destroy() {
        System.out.println("do destroy!");
    }

}

