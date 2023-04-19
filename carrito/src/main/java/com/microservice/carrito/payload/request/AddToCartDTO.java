package com.microservice.carrito.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartDTO {
    private Integer productId;
    private Integer cantidad;
}
