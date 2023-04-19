package com.microservice.carrito.models;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "carrito")
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private Integer userId;
    private Integer product_id;
    @NonNull
    private Date created_date;
    private Integer cantidad;
    public Carrito(){}
}
