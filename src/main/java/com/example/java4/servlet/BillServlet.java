package com.example.java4.servlet;


import com.example.java4.Singleton.HoaDonSingleton;
import com.example.java4.Singleton.KhachHangSingleton;
import com.example.java4.model.HoaDon;
import com.example.java4.model.HoaDonChiTiet;
import com.example.java4.service.HoaDonService;
import com.example.java4.service.SanPhamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet( value={"/bill"})
public class BillServlet extends HttpServlet {
    HoaDonService hoaDonService = new HoaDonService();
    SanPhamService sanPhamService = new SanPhamService();
    ArrayList<HoaDonChiTiet> lstHoaDonChiTiets = new ArrayList<>();
    public void reloadHoaDonChiTiet(){
        lstHoaDonChiTiets = hoaDonService.getAllByIdHoaDon(HoaDonSingleton.getInstance().hoaDon.getIdHoaDon());
    }
    public void init(){
        System.out.println("init success");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if(uri.contains("/bill")){
            double subtotal = 0;
            double total = 0;
            reloadHoaDonChiTiet();

            for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiets) {
                subtotal =subtotal+ hoaDonChiTiet.getChiTietSP().getGiaBan()*hoaDonChiTiet.getSoluong();
            }
            total = subtotal + 20;
            request.setAttribute("khachHang", KhachHangSingleton.getInstance().khachHang);
            request.setAttribute("lstHoaDonChiTiet",lstHoaDonChiTiets);
            request.setAttribute("subtotal",subtotal);
            request.setAttribute("total",total);
            request.setAttribute("hoaDon",HoaDonSingleton.getInstance().hoaDon);
            HoaDonSingleton.getInstance().hoaDon.setTinhTrang(1);
            hoaDonService.updateTinhTrangHD(HoaDonSingleton.getInstance().hoaDon);
            HoaDonSingleton.getInstance().hoaDon = null;
            request.getRequestDispatcher("views/Invoice.jsp").forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
    }
    public void destroy() {
        System.out.println("do destroy!");
    }

}

