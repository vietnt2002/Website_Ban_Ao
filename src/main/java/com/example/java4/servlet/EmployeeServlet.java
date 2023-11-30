package com.example.java4.servlet;

import com.example.java4.model.*;
import com.example.java4.service.NhanVienService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(value = {"/employee", "/addEmployee", "/delete-nhanvien", "/detail-nhanvien","/update-nhanvien"})
public class EmployeeServlet extends HttpServlet {
    ArrayList<NhanVien> lstNhanViens = new ArrayList<>();
    ArrayList<CuaHang> lstCuaHangs = new ArrayList<>();
    ArrayList<ChucVu> lstChucVus = new ArrayList<>();
    NhanVienService nhanVienService = new NhanVienService();
    public void reloadData(){
        lstCuaHangs = nhanVienService.getAllCuaHang();
        lstChucVus  = nhanVienService.getAllChucVu();
        lstNhanViens = nhanVienService.getAllNhanVien();
    }
    public void init() {
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println("do get!");
        if (uri.contains("/employee")) {
            reloadData();
            request.setAttribute("lstCuaHang", lstCuaHangs);
            request.setAttribute("lstChucVu", lstChucVus);
            request.setAttribute("lstNhanVien",lstNhanViens);
            request.getRequestDispatcher("views/Employee.jsp").forward(request, response);
        } else if (uri.contains("/detail-nhanvien")) {
            String id = request.getParameter("id");
            NhanVien nv = new NhanVien();
            for (NhanVien nvn : lstNhanViens) {
                if (nvn.getIdNhanVien().equals(id)) {
                    nv = nvn;
                }
            }
            String dobDateJS = nv.getNgaySinh().toString();
            System.out.println("Test date:" + dobDateJS);
            request.setAttribute("nhanVien",nv);
            request.setAttribute("lstCuaHang",lstCuaHangs);
            request.setAttribute("lstChucVu",lstChucVus);
            request.getRequestDispatcher("views/DetailEmp.jsp").forward(request, response);
        } else if (uri.contains("/delete-nhanvien")) {
            String id = request.getParameter("id");
            NhanVien nv = nhanVienService.getByIDNhanVien(id);
            nhanVienService.deleteNhanvienByID(nv);
            response.sendRedirect("/Home/employee");
        }
           else if(uri.contains("/update-nhanvien")){
                String id = request.getParameter("id");
                Employee newStff = new Employee();
            request.setAttribute("nhanVien",newStff);
            request.getRequestDispatcher("views/UpdateEmp.jsp").forward(request,response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        CuaHang  cuaHang = new CuaHang();
        ChucVu  chucVu  = new ChucVu();
        try {
            if (uri.contains("/addEmployee")) {
                String ma = request.getParameter("maInp");
                String ten = request.getParameter("tenInp");
                String tenDem = request.getParameter("tenDemInp");
                String ho = request.getParameter("hoInp");
                String gioiTinh = request.getParameter("gioiTinhInp");
                Date ngaySinh = Date.valueOf(request.getParameter("ngaySinhInp"));
                String diaChi = request.getParameter("diaChiInp");
                String sdt = request.getParameter("sdtInp");
                String matKhau = request.getParameter("matKhauInp");
                String idCuaHang  = request.getParameter("cuaHangInp");
                String idChucVu  = request.getParameter("chucVuInp");
                int trangThai = Integer.parseInt(request.getParameter("trangThaiInp"));
                for (CuaHang ch : lstCuaHangs) {
                    if (ch.getIdCuaHang().equals(idCuaHang)) {
                        cuaHang = ch;
                    }
                }
                for (ChucVu cv : lstChucVus) {
                    if (cv.getIdChucVu().equals(idChucVu)) {
                        chucVu = cv;
                    }
                }
                nhanVienService.addNhanVien(null,ma,ten,tenDem,ho,gioiTinh,ngaySinh,diaChi,sdt,matKhau,cuaHang,chucVu,trangThai);
                response.sendRedirect("/Home/employee");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("do post!");
//        //testconnection
//        if (uri.contains("/addEmployee")) {
//            System.out.println("do post!");
//            for (Position pos : lstPos) {
//                if (pos.getId().equals(request.getParameter("positionInp"))) {
//                    newPos = pos;
//                }
//            }
//            for (Province prvn : lstProvince) {
//                if (prvn.getId().equals(request.getParameter("idCardPosInp"))) {
//                    newProvince = prvn;
//                }
//            }
//            System.out.println("gender data"+request.getParameter("genderInput"));//pass
//            System.out.println("position data :" + newPos.getName());//pass
//            System.out.println("postion object data:" + newProvince.getName());//pass
//            System.out.println("date js:"+request.getParameter("dobInput"));//pass
//        }
    }

    public void destroy() {

    }

}

