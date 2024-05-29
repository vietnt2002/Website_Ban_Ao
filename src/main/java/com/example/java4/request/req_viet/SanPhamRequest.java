package com.example.java4.request.req_viet;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamRequest {
    private UUID id;

    private String ma;

    @NotBlank(message = "Không được để trống tên")
    private String Ten;

    @NotNull(message = "Không được để trống ngày tạo")
    private Date ngayTao;

    private Integer indx;

    @NotNull(message = "Không được để trống trạng thái")
    private Integer trangThai;
}
