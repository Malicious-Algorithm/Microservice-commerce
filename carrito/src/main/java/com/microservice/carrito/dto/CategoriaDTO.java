package com.microservice.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaDTO {
    @NonNull
    private Integer id_categoria;
    @NonNull
    private String nombre;
    @Nullable
    private String imagenUrl;
    @Nullable
    private String descripcion;
}
