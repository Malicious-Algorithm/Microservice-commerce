package com.microservice.carrito.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaResponseDTO {
    private Integer id;
    private String imagenUrl;
    private String nombre;
    private String descripcion;
}
