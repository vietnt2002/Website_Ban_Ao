package com.example.java4.dto.hoaDon;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    @NotNull(message = "do not empty this field")
    private Integer id;
    @NotNull(message = "do not empty this field")
    private Integer idKH;
    @NotNull(message = "do not empty this field")
    private Integer idNV;
    @NotNull(message = "do not empty this field")
    private Date ngayMuaHang;
    @NotNull(message = "do not empty this field")
    private Integer trangThai;
}