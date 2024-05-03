package com.example.java4.dto.khach_hang;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {
    private Integer id;
    @NotBlank(message = "Do not empty ma")
    private String ma;
    @NotBlank(message = "Do not empty ten")
    private String ten;
    @NotBlank(message = "Do not empty sdt")
    private String sdt;
    @NotNull(message = "Do not empty trang thai")
    private Integer trangThai;
}