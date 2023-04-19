package com.microservice.products.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;


@Getter
@Setter
@Entity(name = "producto")
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Nullable
    private String imagenUrl;
    @NonNull
    private String nombre;
    @NonNull
    private Integer precio;
    @Nullable
    private String descripcion;
    private Integer categoriaId;

    public Product(){}
}
