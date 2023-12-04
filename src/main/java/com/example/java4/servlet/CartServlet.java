package com.example.java4.servlet;
import com.example.java4.Singleton.HoaDonSingleton;
import com.example.java4.model.HoaDonChiTiet;
import com.example.java4.service.HoaDonService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet( value={"/Cart","/Check","/Return"})
public class CartServlet extends HttpServlet {
    HoaDonService hoaDonService = new HoaDonService();
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
        if(uri.contains("/Cart")){
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

