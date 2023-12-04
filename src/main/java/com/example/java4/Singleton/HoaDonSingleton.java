package com.example.java4.Singleton;


import com.example.java4.model.HoaDon;
import com.example.java4.model.KhachHang;
import com.example.java4.service.HoaDonService;

/**
 *
 * @author 84374
 */
public class HoaDonSingleton {

    private static HoaDonSingleton single_instance = null;
    // Declaring a variable of type String
    public HoaDon hoaDon;
    private HoaDonSingleton() {
        hoaDon = null;
    }
    public static synchronized HoaDonSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new HoaDonSingleton();
        }
        return single_instance;
    }
}

