package com.microservice.products.dto;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Integer id;
    private String nombre;
    private String imagenUrl;
    private String descripcion;
}
