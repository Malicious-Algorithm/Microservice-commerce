package com.microservice.products.dto;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {

    @Nullable
    private String imagenUrl;
    @NonNull
    private String nombre;
    @NonNull
    private Integer precio;
    @Nullable
    private String descripcion;

    ProductDTO(){}
}
