package com.example.java4.servlet;


import com.example.java4.model.ChucVu;
import com.example.java4.repositories.ChucVuDAO;
import com.example.java4.repositories.NhanVienDAO;
import com.example.java4.service.NhanVienService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(value = {"/chucvu", "/addChucVu", "/delete-ChucVu", "/detail-ChucVu","/update-chucVu"})
public class ChucVuServlet extends HttpServlet {
    ArrayList<ChucVu> lstChucVus = new ArrayList<>();
    NhanVienService nhanVienService  = new NhanVienService();

    public void reloadData() {
        lstChucVus = nhanVienService.getAllChucVu();
    }

    public void init() {
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/chucvu")) {
            reloadData();
            request.setAttribute("lstChucVu", lstChucVus);
            request.getRequestDispatcher("views/ChucVu.jsp").forward(request, response);
        }
        if (uri.contains("/detail-ChucVu")) {
            String id = request.getParameter("id");
            ChucVu chucVu = new ChucVu();
            for (ChucVu chucVus : lstChucVus) {
                if (chucVus.getIdChucVu().equals(id)) {
                    chucVu = chucVus;
                }
            }
            request.setAttribute("chucVu",chucVu);
            request.getRequestDispatcher("views/DetailChucVu.jsp").forward(request,response);
        }
        if(uri.contains("/delete-ChucVu")){
            String id = request.getParameter("id");
            ChucVu chucVu  = new ChucVu();
            chucVu = nhanVienService.getByID(id);
            nhanVienService.deleteChucVu(chucVu);
        }
        System.out.println("do get!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
        String uri = request.getRequestURI();
        if(uri.contains("/addChucVu")){
             String ma = request.getParameter("maInp");
             String ten = request.getParameter("tenInp");
             nhanVienService.addChucVu(null,ma,ten);
        }
        response.sendRedirect("/Home/chucvu");
    }

    public void destroy() {
        System.out.println("do destroy!");
    }

}

