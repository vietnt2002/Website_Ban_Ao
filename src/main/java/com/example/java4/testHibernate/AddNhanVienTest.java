package com.example.java4.testHibernate;

import com.example.java4.model.ChucVu;
import com.example.java4.model.CuaHang;
import com.example.java4.model.NhanVien;
import com.example.java4.repositories.ChucVuDAO;
import com.example.java4.repositories.CuaHangDAO;
import com.example.java4.repositories.NhanVienDAO;

import java.sql.Date;

public class AddNhanVienTest {
    public static void main(String[] args) {
        NhanVienDAO nhanVienDAO  = new NhanVienDAO();
        CuaHangDAO cuaHangDAO = new CuaHangDAO();
        ChucVuDAO chucVuDAO = new ChucVuDAO();
//        String idNV, String ma, String ten, String temDem, String ho, String gioiTinh, Date ngaySinh, String diaChi, String sdt, String matKhau, CuaHang
//        ch, ChucVu cv, String idGuiBC, int trangThai
        CuaHang cuaHang = cuaHangDAO.getByID("345E8703-4284-486C-BAE9-08B8C1E7B3DB");
        ChucVu  chucVu  = chucVuDAO.getByID("557D0641-427C-42B4-B7DF-932F85446333");
        nhanVienDAO.add(null,"NV1","Loc","Phuc","Nguyen","Male",new Date(1997,03,02),"Quang Ninh","0374223222","loc123456",cuaHang,chucVu,1);

    }
}
