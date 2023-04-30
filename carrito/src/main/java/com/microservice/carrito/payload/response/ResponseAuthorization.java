package com.microservice.carrito.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseAuthorization {
    private String email;
    private Integer id;
    private String nombre;
    private String apellido;
    private String password;

    public ResponseAuthorization(){}
}