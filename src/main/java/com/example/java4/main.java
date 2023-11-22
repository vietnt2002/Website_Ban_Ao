package com.example.java4;

import com.example.java4.model.*;
import com.example.java4.repositories.ClssDAO;
import com.example.java4.repositories.StudentDAO;

import java.sql.Date;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        Clss clss1 = new Clss(null,"clss1");
        Clss clss2 = new Clss(null,"clss2");
        Clss clss3 = new Clss(null,"clss3");
//        StudentDAO studentDAO = new StudentDAO();
        ClssDAO clssDAO  = new ClssDAO();
        clssDAO.addClss(1,"clss1");
//      Student student1 = new Student(null,"John Doe", "123 Main St", "Male",clss1 , "175 cm", "70 kg");
//      studentDAO.addStudent(null,"John Doe", "123 Main St", "Male",clss1 , "175 cm", "70 kg");
    }
}
