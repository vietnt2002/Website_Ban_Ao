package com.example.java4.dto.hdct;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    private Integer id;
    @NotNull(message = "do not empty this field")
    private Integer idHoaDon;
    @NotNull(message = "do not empty this field")
    private Integer idSPCT;
    @NotNull(message = "do not empty this field")
    private Integer soLuong;
    @NotNull(message = "do not empty this field")
    private Integer donGia;
    @NotNull(message = "do not empty this field")
    private Timestamp thoiGian;
    @NotNull(message = "do not empty this field")
    private Integer trangThai;
}