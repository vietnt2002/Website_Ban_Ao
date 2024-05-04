package com.example.java4.response;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MauSacResponse {
    private Integer id;
    private String ma;
    private String ten;
    private Integer trangThai;
}
