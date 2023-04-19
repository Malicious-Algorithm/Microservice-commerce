package com.microservice.carrito.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Integer id;
    private String imagenUrl;
    private String nombre;
    private Integer precio;
    private String descripcion;
    private Integer categoriaId;

}
