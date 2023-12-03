package com.example.java4.servlet;

import com.example.java4.model.DongSP;
import com.example.java4.model.NSX;
import com.example.java4.service.SanPhamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

//,"/add","/delete","/detail"
@WebServlet(value = {"/nsx","/detail-nsx","/routeUpdate-nsx","/addNsx","/delete-nsx","/update-nsx"})
public class NsxServlet extends HttpServlet {
    public ArrayList<NSX> lstNsxes = new ArrayList<>();
    SanPhamService service = new SanPhamService();

    public void reload() {
        lstNsxes = service.getAllNSX();
    }

    public void init() {
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do get!");
        String uri = request.getRequestURI();
        if (uri.contains("/nsx")) {
            reload();
            request.setAttribute("lstNsx", lstNsxes);
            request.getRequestDispatcher("views/Nsx.jsp").forward(request, response);
        }
        if (uri.contains("/detail-nsx")) {
            String id = request.getParameter("id");
            NSX nsx = new NSX();
            for (NSX n : lstNsxes) {
                if (n.getIdNSX().equals(id)) {
                    nsx = n;
                }
            }
            request.setAttribute("nsx", nsx);
            request.getRequestDispatcher("views/ShowNsx.jsp").forward(request,response);
        }
        if(uri.contains("/routeUpdate-nsx")){
            String id = request.getParameter("id");
            NSX nsx = new NSX();
            for (NSX n  : lstNsxes) {
                if(n.getIdNSX().equals(id)){
                    nsx = n;
                }
            }
            request.setAttribute("nsx",nsx);
            request.getRequestDispatcher("views/ShowNsx.jsp").forward(request,response);
        }
        if(uri.contains("/delete-nsx")){
            String id = request.getParameter("id");
            NSX nsx = new NSX();
            for (NSX n : lstNsxes) {
                if (n.getIdNSX().equals(id)) {
                    nsx = n;
                }
            }
            service.deleteNsx(nsx);
            response.sendRedirect("/Home/nsx");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post!");
        String uri = request.getRequestURI();
        if(uri.contains("/addNsx")){
            String id = request.getParameter("idInp");
            String ma = request.getParameter("maInp");
            String ten = request.getParameter("tenInp");
            service.addNsx(id,ma,ten);
            response.sendRedirect("/Home/nsx");
        }
        if(uri.contains("/update-nsx")){
            String id = request.getParameter( "idInp");
            String ma = request.getParameter("maInp");
            String ten = request.getParameter("tenInp");
            service.updateNsx(id,ma,ten);
            response.sendRedirect("/Home/nsx");
        }
    }

    public void destroy() {
        System.out.println("do destroy!");
    }

}

