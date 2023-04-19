package com.microservice.carrito.dto;

import com.microservice.carrito.payload.response.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CarritoDTO {

    private List<ProductDTO> productos;
    @NonNull
    private String user;
    @NonNull
    private Date created_date;
    private Integer cantidad;
}
