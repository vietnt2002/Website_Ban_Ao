package com.example.java4.servlet;

import com.example.java4.model.ChucVu;
import com.example.java4.model.CuaHang;
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

@WebServlet(value = {"/cuaHang", "/addCuaHang", "/delete-CuaHang", "/detail-CuaHang","/update-CuaHang"})
public class CuaHangServlet extends HttpServlet {
    ArrayList<CuaHang> lstCuaHangs = new ArrayList<>();
    NhanVienService nhanVienService  = new NhanVienService();
    public void reloadData() {
        lstCuaHangs = nhanVienService.getAllCuaHang();
    }

    public void init() {
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/cuaHang")) {
            reloadData();
            request.setAttribute("lstCuaHang", lstCuaHangs);
            request.getRequestDispatcher("views/CuaHang.jsp").forward(request, response);
        }
        if (uri.contains("/detail-cuaHang")) {
            String id = request.getParameter("id");
            CuaHang cuaHang  = new CuaHang();
            for (CuaHang ch : lstCuaHangs) {
                    if(ch.getIdCuaHang().equals(id)){
                         cuaHang = ch;
                    }
            }
            request.setAttribute("cuaHang",cuaHang);
            request.getRequestDispatcher("views/DetailCuaHang.jsp").forward(request,response);
        }
        if(uri.contains("/delete-CuaHang")){
            String id = request.getParameter("id");
            CuaHang cuaHang = new CuaHang();
            cuaHang= nhanVienService.getByIDCuaHang(id);
            nhanVienService.deleteCuaHang(cuaHang);
            response.sendRedirect("/home/cuaHang");
        }
        System.out.println("do get!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
        String uri = request.getRequestURI();
        if(uri.contains("/addCuaHang")){
            String ma = request.getParameter("maInp");
            String ten = request.getParameter("tenInp");
            String diaChi = request.getParameter("diaChiInp");
            String thanhPho = request.getParameter("thanhPhoInp");
            String quocGia  = request.getParameter("quocGiaInp");
            nhanVienService.addCuaHang(null,ma,ten,diaChi,thanhPho,quocGia);
            response.sendRedirect("/Home/cuaHang");
        }
    }

    public void destroy() {
        System.out.println("do destroy!");
    }

}

