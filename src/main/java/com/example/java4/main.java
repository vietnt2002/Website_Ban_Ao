package com.example.java4;

import com.example.java4.model.*;
import com.example.java4.repositories.*;

import java.sql.Date;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
//        String id, String ma, String ten, String tenDem, String ho, Date ngaySinh, String sdt, String diaChi, String quocGia, String matKhau
          KhachHang kh = new KhachHang(null,"NV01","Loc","Phuc","Nguyen",new Date(1997,03,02),"0374223222","Quang Ninh","VietNam","loc123");
          KhachHangDAO dao = new KhachHangDAO();
          dao.addKhachHang(null,"NV01","Loc","Phuc","Nguyen",new Date(1997,03,02),"0374223222","Quang Ninh","VietNam","loc123");
    }
}
