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

@WebServlet(value = {"/chiTietSP", "/routeUpdate-chiTietSP", "/addChiTietSP", "/delete-chiTietSP", "/detail-chiTietSP","/update-chiTietSP"})
public class ChiTietSPServlet extends HttpServlet {
    SanPhamService service = new SanPhamService();
    ArrayList<SanPham> lstSanPhams = new ArrayList<>();
    ArrayList<ChiTietSP> lstChiTietSPS = new ArrayList<>();
    ArrayList<MauSac> lstMauSacs = new ArrayList<>();
    ArrayList<DongSP> lstDongSPS = new ArrayList<>();
    ArrayList<NSX> lstNSXes = new ArrayList<>();

    public void reload() {
        lstSanPhams = service.getAllSP();
        lstChiTietSPS = service.getAllChiTietSP();
        lstMauSacs = service.getAllMauSac();
        lstDongSPS = service.getAllDongSP();
        lstNSXes = service.getAllNSX();
    }

    public void reloadSelectOP(){
        lstSanPhams = service.getAllSP();
        lstMauSacs = service.getAllMauSac();
        lstDongSPS = service.getAllDongSP();
        lstNSXes = service.getAllNSX();
    }

    public void init() {
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if (uri.contains("/chiTietSP")) {
            reload();
            request.setAttribute("lstSanPham", lstSanPhams);
            request.setAttribute("lstChiTietSP", lstChiTietSPS);
            request.setAttribute("lstMauSac", lstMauSacs);
            request.setAttribute("lstDongSP", lstDongSPS);
            request.setAttribute("lstNSX", lstNSXes);
            request.getRequestDispatcher("views/ChiTietSanPham.jsp").forward(request, response);
        }

        if (uri.contains("/detail-chiTietSP")) {

            String id = request.getParameter("id");
            ChiTietSP chiTietSP = new ChiTietSP();
            for (ChiTietSP ctsp : lstChiTietSPS) {
                if (ctsp.getIdChiTietSP().equals(id)) {
                    chiTietSP = ctsp;
                }
            }
            request.setAttribute("lstSanPham",lstSanPhams);
            request.setAttribute("lstNSX", lstNSXes);
            request.setAttribute("lstMauSac",lstMauSacs);
            request.setAttribute("lstDongSP",lstDongSPS);
            request.setAttribute("chiTietSP", chiTietSP);
            request.getRequestDispatcher("views/showChiTietSanPham.jsp").forward(request, response);
        }

        if (uri.contains("/routeUpdate-chiTietSP")) {
            String id = request.getParameter("id");
            ChiTietSP chiTietSP = new ChiTietSP();
            for (ChiTietSP ctsp : lstChiTietSPS) {
                if (ctsp.getIdChiTietSP().equals(id)) {
                    chiTietSP = ctsp;
                }
            }
            request.setAttribute("lstSanPham",lstSanPhams);
            request.setAttribute("lstNSX", lstNSXes);
            request.setAttribute("lstMauSac",lstMauSacs);
            request.setAttribute("lstDongSP",lstDongSPS);
            request.setAttribute("chiTietSP", chiTietSP);
            request.getRequestDispatcher("views/showChiTietSanPham.jsp").forward(request, response);
        }
        if(uri.contains("/delete-chiTietSP")){
            String id = request.getParameter("id");
            ChiTietSP chiTietSP = new ChiTietSP();
            for (ChiTietSP ctsp : lstChiTietSPS) {
                 chiTietSP = ctsp;
            }
            service.deleteChiTietSP(chiTietSP);
            response.sendRedirect("/Home/chiTietSP");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
        String uri = request.getRequestURI();
        if(uri.contains("/addChiTietSP")){
            String idSanPham = request.getParameter("sanPhamInp");
            String idNSX = request.getParameter("nsxInp");
            String idMauSac  = request.getParameter("mauSacInp");
            String idDongSP  = request.getParameter("dongSPInp");
            SanPham sanPham = service.getByIDSP(idSanPham);
            NSX nsx = service.getByIDNSX(idNSX);
            MauSac mauSac = service.getByIdMauSac(idMauSac);
            DongSP dongSP  = service.getByIDDongSP(idDongSP);
            int namBH = Integer.parseInt(request.getParameter("namBHInp"));
            String mota = request.getParameter("motaInp");
            int soLuongTon = Integer.parseInt(request.getParameter("soLuongTonInp"));
            Double giaNhap = Double.valueOf(request.getParameter("giaNhapInp"));
            Double giaBan = Double.valueOf(request.getParameter("giaBanInp"));
            service.addChiTietSP(null,sanPham,nsx,mauSac,dongSP,namBH,mota,soLuongTon,giaNhap,giaBan);
            response.sendRedirect("/Home/chiTietSP");
        }

        if(uri.contains("/update-chiTietSP")){
            String id = request.getParameter("idInp");
            String idSanPham = request.getParameter("sanPhamInp");
            String idNSX = request.getParameter("nsxInp");
            String idMauSac  = request.getParameter("mauSacInp");
            String idDongSP  = request.getParameter("dongSPInp");
            SanPham sanPham = service.getByIDSP(idSanPham);
            NSX nsx = service.getByIDNSX(idNSX);
            MauSac mauSac = service.getByIdMauSac(idMauSac);
            DongSP dongSP  = service.getByIDDongSP(idDongSP);
            int namBH = Integer.parseInt(request.getParameter("namBHInp"));
            String mota = request.getParameter("motaInp");
            int soLuongTon = Integer.parseInt(request.getParameter("soLuongTonInp"));
            Double giaNhap = Double.valueOf(request.getParameter("giaNhapInp"));
            Double giaBan = Double.valueOf(request.getParameter("giaBanInp"));
            ChiTietSP chiTietSP = new ChiTietSP(id,sanPham,nsx,mauSac,dongSP,namBH,mota,soLuongTon,giaNhap,giaBan);
            service.updateChiTietSP(chiTietSP);
            response.sendRedirect("/Home/chiTietSP");
        }
    }

    public void destroy() {
        System.out.println("do destroy!");
    }
}

