package com.microservice.carrito.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private ProductResponseDTO producto;
    private CategoriaResponseDTO categoria;

    public ProductDTO(){}
}
