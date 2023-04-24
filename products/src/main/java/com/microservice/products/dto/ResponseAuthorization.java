package com.microservice.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseAuthorization {
    private String token;
    private String email;
    private Integer id;
    private String nombre;
}
