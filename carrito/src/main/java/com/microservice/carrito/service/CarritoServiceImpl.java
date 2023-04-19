package com.microservice.carrito.service;


import com.microservice.carrito.dto.CarritoDTO;
import com.microservice.carrito.models.Carrito;
import com.microservice.carrito.payload.request.AddToCartDTO;
import com.microservice.carrito.payload.response.ProductDTO;
import com.microservice.carrito.payload.response.UserResponseDTO;
import com.microservice.carrito.repository.CarritoReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class CarritoServiceImpl implements CarritoService{

    @Autowired
    private CarritoReposity carritoReposity;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addToCarrito(Integer id_user, AddToCartDTO addToCartDTO) {
        ProductDTO productoExistente = restTemplate.getForEntity("http://localhost:9091/products/productById/"+addToCartDTO.getProductId(), ProductDTO.class).getBody();
        Carrito carrito = new Carrito();
        carrito.setUserId(id_user);
        carrito.setCantidad(addToCartDTO.getCantidad());
        carrito.setProduct_id(productoExistente.getProducto().getId());
        carrito.setCreated_date(new Date());
        carritoReposity.save(carrito);
    }

    @Override
    public List<ProductDTO> getAllProductsByIdUser(Integer id_user) {
        UserResponseDTO userExistente = restTemplate.getForEntity("http://localhost:9093/cliente/getClienteById/"+id_user, UserResponseDTO.class).getBody();
        List<Carrito> carritoList = carritoReposity.findByUserId(userExistente.getId());
        List<ProductDTO> productDTO = new ArrayList<>();
        //anda a buscarme los productos que pertenezcan a este user.
        for (Carrito productos: carritoList) {
            ProductDTO productoExistente = restTemplate.getForEntity("http://localhost:9091/products/productById/"+productos.getProduct_id(), ProductDTO.class).getBody();
            productoExistente.setProducto(productoExistente.getProducto());
            productoExistente.setCategoria(productoExistente.getCategoria());
            productDTO.add(productoExistente);
        }
        return productDTO;
    }
}
