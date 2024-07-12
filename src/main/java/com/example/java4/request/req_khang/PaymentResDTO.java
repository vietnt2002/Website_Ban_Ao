package com.example.java4.request.req_khang;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentResDTO implements Serializable {

    private String status;
    private String message;
    private String URL;

}
