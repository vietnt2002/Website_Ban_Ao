package com.example.java4.servlet;
import com.example.java4.Singleton.HoaDonSingleton;
import com.example.java4.Singleton.KhachHangSingleton;
import com.example.java4.model.ChiTietSP;
import com.example.java4.model.HoaDon;
import com.example.java4.model.HoaDonChiTiet;
import com.example.java4.model.KhachHang;
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
import java.util.Calendar;

@WebServlet( value={"/Cart","/Check","/Return"})
public class CartServlet extends HttpServlet {
    HoaDonService hoaDonService = new HoaDonService();
    SanPhamService sanPhamService = new SanPhamService();
    ArrayList<HoaDonChiTiet> lstHoaDonChiTiets = new ArrayList<>();
    public void reloadHoaDonChiTiet(){
        lstHoaDonChiTiets = hoaDonService.getAllByIdHoaDon(HoaDonSingleton.getInstance().hoaDon.getIdHoaDon());
    }
    public void addChiTietSP(HoaDon hoaDon,ChiTietSP chiTietSP, int soLuong,double donGia){
        hoaDonService.addHoaDonChiTiet(hoaDon,chiTietSP,soLuong,donGia);
    }
    public HoaDon startTraceHoaDon(KhachHang kh){
        return HoaDonSingleton.getInstance().hoaDon = hoaDonService.getHoaDonAvai(kh);
    }
    public HoaDon createNewHoaDon(KhachHang kh){
        java.sql.Date sqlDateNow = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        int count = hoaDonService.getAllHoaDon().size();
        return hoaDonService.add(null,kh,null,count+1,sqlDateNow, null, null, null, 0,kh.getTen(),kh.getDiaChi(),kh.getSdt());
    }
    public void init(){
        System.out.println("init success");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if(uri.contains("/Cart")){
            if(startTraceHoaDon(KhachHangSingleton.getInstance().khachHang)==null){
                createNewHoaDon(KhachHangSingleton.getInstance().khachHang);
                System.out.println(startTraceHoaDon(KhachHangSingleton.getInstance().khachHang));
            }
            String id = request.getParameter("id");
            ChiTietSP chiTietSP  = sanPhamService.getByIDChiTietSP(id);
            double  subtotal = 0;
            double  total = 0;
            addChiTietSP(HoaDonSingleton.getInstance().hoaDon,chiTietSP,1,0);
            reloadHoaDonChiTiet();
            for (HoaDonChiTiet hoaDonChiTiet : lstHoaDonChiTiets) {
                    subtotal =subtotal+ hoaDonChiTiet.getChiTietSP().getGiaBan()*hoaDonChiTiet.getSoluong();
            }
            total = subtotal + 20;
            request.setAttribute("hoaDon",HoaDonSingleton.getInstance().hoaDon);
            request.setAttribute("subtotal",subtotal);
            request.setAttribute("total",total);
            request.setAttribute("lstHoaDonChiTiet",lstHoaDonChiTiets);
            request.getRequestDispatcher("views/Cart.jsp").forward(request,response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
    }
    public void destroy() {
        System.out.println("do destroy!");
    }

}

