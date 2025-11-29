package com.factura.factura.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResponseFormat {

    private int statusCode;
    private String statusMessage;
    private Object data;

}
