package com.microservice.carrito.service;


import com.microservice.carrito.dto.CarritoDTO;
import com.microservice.carrito.models.Carrito;
import com.microservice.carrito.payload.request.AddToCartDTO;
import com.microservice.carrito.payload.response.ProductDTO;
import com.microservice.carrito.payload.response.UserResponseDTO;
import com.microservice.carrito.repository.CarritoReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.http.HttpHeaders;


@Service
public class CarritoServiceImpl implements CarritoService{

    @Autowired
    private CarritoReposity carritoReposity;

    @Autowired
    private RestTemplate restTemplate;

    private final HttpHeaders headers = new HttpHeaders();


    @Override
    public void addToCarrito(Integer id_user, AddToCartDTO addToCartDTO,String authorizationReq) {
        headers.set("Authorization",authorizationReq);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ProductDTO productoExistente = restTemplate.exchange("http://products:9091/products/productById/"+addToCartDTO.getProductId(), HttpMethod.GET,request, ProductDTO.class).getBody();
        Carrito carrito = new Carrito();
        carrito.setUserId(id_user);
        carrito.setCantidad(addToCartDTO.getCantidad());
        carrito.setProduct_id(productoExistente.getProducto().getId());
        carrito.setCreated_date(new Date());
        carritoReposity.save(carrito);
    }

    @Override
    public List<ProductDTO> getAllProductsByIdUser(Integer id_user, String authorizationReq) {
        headers.set("Authorization",authorizationReq);
        HttpEntity<String> request = new HttpEntity<>(headers);
        UserResponseDTO userExistente = restTemplate.exchange("http://cliente:9093/cliente/getClienteById/"+id_user, HttpMethod.GET,request, UserResponseDTO.class).getBody();
        List<Carrito> carritoList = carritoReposity.findByUserId(userExistente.getId());
        List<ProductDTO> productDTO = new ArrayList<>();
        //anda a buscarme los productos que pertenezcan a este user.
        for (Carrito productos: carritoList) {
            ProductDTO productoExistente = restTemplate.exchange("http://products:9091/products/productById/"+productos.getProduct_id(), HttpMethod.GET,request, ProductDTO.class).getBody();
            productoExistente.setProducto(productoExistente.getProducto());
            productoExistente.setCategoria(productoExistente.getCategoria());
            productDTO.add(productoExistente);
        }
        return productDTO;
    }

    @Override
    public void deleteFromCarrito(Integer id_usuario, Integer id_producto, String authorizationReq) {
        headers.set("Authorization",authorizationReq);
        HttpEntity<String> request = new HttpEntity<>(headers);
        UserResponseDTO userExistente = restTemplate.exchange("http://cliente:9093/cliente/getClienteById/" + id_usuario, HttpMethod.GET,request, UserResponseDTO.class).getBody();
        List<Carrito> carritoList = carritoReposity.findByUserId(userExistente.getId());
        for (Carrito productos : carritoList) {
            if(productos.getProduct_id().equals(id_producto)){
                ProductDTO productoExistente = restTemplate.exchange("http://products:9091/products/productById/" + id_producto, HttpMethod.GET,request, ProductDTO.class).getBody();
                productos.setProduct_id(productoExistente.getProducto().getId());
                carritoReposity.delete(productos);
                break;
            }
        }
    }
}
