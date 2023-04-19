package com.microservice.carrito.service;


import com.microservice.carrito.dto.CarritoDTO;
import com.microservice.carrito.payload.request.AddToCartDTO;
import com.microservice.carrito.payload.response.ProductDTO;

import java.util.List;

public interface CarritoService {
    public void addToCarrito(Integer id_user, AddToCartDTO addToCartDTO);
    public List<ProductDTO> getAllProductsByIdUser(Integer id_user);
}
