package com.microservice.carrito.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private Integer id;
    private String apellido;
    private String nombre;
    private String email;
    private String password;

}
