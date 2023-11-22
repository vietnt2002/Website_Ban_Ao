package com.example.java4.servlet;

import com.example.java4.model.Employee;
import com.example.java4.model.Position;
import com.example.java4.model.Province;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(value = {"/employee", "/addEmployee", "/delete", "/detail","/update"})
public class EmployeeServlet extends HttpServlet {
    ArrayList<Employee> lstEmpt = new ArrayList<>();
    ArrayList<Position> lstPos = new ArrayList<>();
    ArrayList<Province> lstProvince = new ArrayList<>();

    public void rootDataTest() {
        Position pos1 = new Position("pos1", "CHF1", "Chief");
        Position pos2 = new Position("pos2", "EGN1", "Engineer");
        Position pos3 = new Position("pos3", "CHF1", "Chief");
        Province pro1 = new Province("pro1", "14", "Quang Ninh");
        Province pro2 = new Province("pro2", "15", "Hai Phong");
        Province pro3 = new Province("pro3", "29", "Ha Noi");
        Employee emp1 = new Employee("id1", "nv1", "Nguyễn Phúc Lộc", Date.valueOf("2023-02-05"), "Male", pos1, "0125245454489", Date.valueOf("2023-05-05"), pro1, "Ha Noi", "0374223222", "phuclocub@gmail.com");
        Employee emp2 = new Employee("id2", "nv2", "Nguyễn Phúc Lộc", Date.valueOf("2023-02-05"), "Male", pos2, "0125245454489", Date.valueOf("2023-05-05"), pro2, "Ha Noi", "0374223222", "phuclocub@gmail.com");
        Employee emp3 = new Employee("id3", "nv3", "Nguyễn Phúc Lộc", Date.valueOf("2023-02-05"), "Male", pos3, "0125245454489", Date.valueOf("2023-05-05"), pro3, "Ha Noi", "0374223222", "phuclocub@gmail.com");
        lstEmpt.add(emp1);
        lstEmpt.add(emp2);
        lstEmpt.add(emp3);
        lstPos.add(pos1);
        lstPos.add(pos2);
        lstPos.add(pos3);
        lstProvince.add(pro1);
        lstProvince.add(pro2);
        lstProvince.add(pro3);
    }

    public void init() {
        rootDataTest();
        System.out.println("init success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println("do get!");
        if (uri.contains("/employee")) {
            request.setAttribute("lstStaff", lstEmpt);
            request.setAttribute("lstPos", lstPos);
            request.setAttribute("lstProvince", lstProvince);
            request.getRequestDispatcher("views/Employee.jsp").forward(request, response);
        } else if (uri.contains("/detail")) {
            String id = request.getParameter("id");
            Employee stff = new Employee();
            for (Employee emp : lstEmpt) {
                if (emp.getId().equals(id)) {
                    stff = emp;
                }
            }
            String dobDateJS = stff.getDob().toString();
            String idCardDateJS = stff.getIdCardNo().toString();
            System.out.println("Test date:" + dobDateJS);
            request.setAttribute("staff", stff);
            request.setAttribute("position", stff.getPosition());
            request.setAttribute("province", stff.getProvince());
            request.getRequestDispatcher("views/Detail.jsp").forward(request, response);
        } else if (uri.contains("/delete")) {
            String id = request.getParameter("target");
            for (int i = 0; i < lstEmpt.size(); i++) {
                if(lstEmpt.get(i).getId().equals(id)){
                     lstEmpt.remove(i);
//                    System.out.println("target to"+ i);
                }
            }
            System.out.println("delete");
            response.sendRedirect("/Home/employee");
        }
           else if(uri.contains("/update")){
                String id = request.getParameter("target");
                Employee newStff = new Employee();
            for (Employee emp : lstEmpt) {
                Employee stff  = new Employee();
                if (emp.getId().equals(id)) {
                    stff = emp;
                }
            }
            request.setAttribute("staff",newStff);
            request.getRequestDispatcher("views/Update.jsp").forward(request,response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        Province newProvince = new Province();
        Position newPos = new Position();
        if (uri.contains("/addEmployee")) {
            String newID = "id" + lstEmpt.size() + 1;
            String newCode = "nv" + lstEmpt.size() + 1;
            String fullName = request.getParameter("fullNameInp");
            String dob = request.getParameter("dobInp");
            String gender = request.getParameter("genderInp");
            String Idposition = request.getParameter("positionInp");
            String idCardNo = request.getParameter("idCardNoInp");
            String idCardDate = request.getParameter("idCardDateInp");
            String idProvince = request.getParameter("idCardPosInp");
            String residence = request.getParameter("residenceInp");
            String phone = request.getParameter("phoneInp");
            String email = request.getParameter("emailInp");
            for (Position pos : lstPos) {
                if (pos.getId().equals(Idposition)) {
                    newPos = pos;
                }
            }
            for (Province prvn : lstProvince) {
                if (prvn.getId().equals(idProvince)) {
                    newProvince = prvn;
                }
            }
            Employee emp = new Employee(newID, newCode, fullName, Date.valueOf(dob), gender, newPos, idCardNo, Date.valueOf(idCardDate), newProvince, residence, phone, email);
            lstEmpt.add(emp);
            response.sendRedirect("/Home/employee");
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

