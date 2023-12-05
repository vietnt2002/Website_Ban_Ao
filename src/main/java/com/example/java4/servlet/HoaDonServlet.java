package com.example.java4.servlet;

import com.example.java4.Singleton.KhachHangSingleton;
import com.example.java4.model.*;
import com.example.java4.service.HoaDonService;
import com.example.java4.service.KhachHangService;
import com.example.java4.service.NhanVienService;
import com.example.java4.service.SanPhamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = {"/hoaDon", "/routeUpdate-hoaDon", "/delete-hoaDon", "/detail-hoaDon"})
public class HoaDonServlet extends HttpServlet {
    HoaDonService hoaDonService  = new HoaDonService();
    KhachHangService khachHangService = new KhachHangService();
    NhanVienService nhanVienService = new NhanVienService();
    ArrayList<HoaDon> lstHoaDons = new ArrayList<>();
    public void reload() {
        lstHoaDons = hoaDonService.getAllHoaDon();
    }

    public void reloadSelectOP(){

    }

    public void init() {
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if (uri.contains("/hoaDon")) {
            reload();
            request.setAttribute("lstHoaDon", lstHoaDons);
            request.getRequestDispatcher("views/HoaDon.jsp").forward(request, response);
        }

        if (uri.contains("/detail-hoaDon")) {
            String id = request.getParameter("id");
            HoaDon  hoaDon = new HoaDon();
            double subtotal  = 0;
            double total = 0;
            for (HoaDon hd : lstHoaDons) {
                if (hd.getIdHoaDon().equals(id)) {
                    hoaDon = hd;
                }
            }
            ArrayList<HoaDonChiTiet> lstHoaDonChiTiet  = hoaDonService.getAllByIdHoaDon(hoaDon.getIdHoaDon()) ;
            for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiet) {
                subtotal =subtotal+ hoaDonChiTiet.getChiTietSP().getGiaBan()*hoaDonChiTiet.getSoluong();
            }
            total = subtotal + 20;
            request.setAttribute("subtotal",subtotal);
            request.setAttribute("total",total);
            request.setAttribute("hoaDon", hoaDon);
            request.setAttribute("lstHoaDonChiTiet",lstHoaDonChiTiet);
            request.setAttribute("khachHang", hoaDon.getKH());
            request.getRequestDispatcher("views/Invoice.jsp").forward(request, response);
        }

        if (uri.contains("/routeUpdate-hoaDon")) {
            String id = request.getParameter("id");
            HoaDon hoaDon = new HoaDon();
            for (HoaDon hd : lstHoaDons ) {
                if (hd.getIdHoaDon().equals(id)) {
                    hoaDon = hd;
                }
            }
            request.setAttribute("hoaDon", hoaDon);
            request.getRequestDispatcher("views/showHoaDon.jsp").forward(request, response);
        }
//        if(uri.contains("/delete-hoaDon")){
//            String id = request.getParameter("id");
//            HoaDon hoaDon  = new HoaDon();
//            for (HoaDon hd : lstHoaDons) {
//                 hoaDon = hd;
//            }
//            hoaDonService.deleteHoaDon(hoaDon);
//            response.sendRedirect("/Home/chiTietSP");
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
        String uri = request.getRequestURI();
//        if(uri.contains("/addHoaDon")){
//            String idSanPham = request.getParameter("sanPhamInp");
//            String idNSX = request.getParameter("nsxInp");
//            String idMauSac  = request.getParameter("mauSacInp");
//            String idDongSP  = request.getParameter("dongSPInp");
//            SanPham sanPham = service.getByIDSP(idSanPham);
//            NSX nsx = service.getByIDNSX(idNSX);
//            MauSac mauSac = service.getByIdMauSac(idMauSac);
//            DongSP dongSP  = service.getByIDDongSP(idDongSP);
//            int namBH = Integer.parseInt(request.getParameter("namBHInp"));
//            String mota = request.getParameter("motaInp");
//            int soLuongTon = Integer.parseInt(request.getParameter("soLuongTonInp"));
//            Double giaNhap = Double.valueOf(request.getParameter("giaNhapInp"));
//            Double giaBan = Double.valueOf(request.getParameter("giaBanInp"));
//            String link =  request.getParameter("linkInp");
//            service.addChiTietSP(null,sanPham,nsx,mauSac,dongSP,namBH,mota,soLuongTon,giaNhap,giaBan,link);
//            response.sendRedirect("/Home/chiTietSP");
//        }

//        if(uri.contains("/update-hoaDon")){
//            String id = request.getParameter("idInp");
//            String idSanPham = request.getParameter("sanPhamInp");
//            String idNSX = request.getParameter("nsxInp");
//            String idMauSac  = request.getParameter("mauSacInp");
//            String idDongSP  = request.getParameter("dongSPInp");
//            SanPham sanPham = service.getByIDSP(idSanPham);
//            NSX nsx = service.getByIDNSX(idNSX);
//            MauSac mauSac = service.getByIdMauSac(idMauSac);
//            DongSP dongSP  = service.getByIDDongSP(idDongSP);
//            int namBH = Integer.parseInt(request.getParameter("namBHInp"));
//            String mota = request.getParameter("motaInp");
//            int soLuongTon = Integer.parseInt(request.getParameter("soLuongTonInp"));
//            Double giaNhap = Double.valueOf(request.getParameter("giaNhapInp"));
//            Double giaBan = Double.valueOf(request.getParameter("giaBanInp"));
//            String link  =  request.getParameter("linkInp");
//            ChiTietSP chiTietSP = new ChiTietSP(id,sanPham,nsx,mauSac,dongSP,namBH,mota,soLuongTon,giaNhap,giaBan,link);
//            service.updateChiTietSP(chiTietSP);
//            response.sendRedirect("/Home/chiTietSP");
//        }


    }

    public void destroy() {
        System.out.println("do destroy!");
    }
}

