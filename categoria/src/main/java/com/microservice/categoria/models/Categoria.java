package com.microservice.categoria.models;

import javax.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@Entity(name = "categoria")
@AllArgsConstructor
@RequiredArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String nombre;
    @Nullable
    private String imagenUrl;
    @Nullable
    private String descripcion;

    public Categoria(){}
}
