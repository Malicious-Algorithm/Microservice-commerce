package com.microservice.carrito.controllers;

import com.microservice.carrito.dto.CarritoDTO;
import com.microservice.carrito.models.Carrito;
import com.microservice.carrito.payload.request.AddToCartDTO;
import com.microservice.carrito.payload.response.ProductDTO;
import com.microservice.carrito.service.CarritoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
@Slf4j
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/addToCarrito/{id_user}")
    //este va a recibir el Authorization y en el le va a llegar el id del usuario, pero por ahora se lo pasamos nosotros
    public ResponseEntity<String> addToCarrito(/*@Authorization String authorization*/@PathVariable Integer id_user,@RequestBody AddToCartDTO addToCartDTO){
        /*proceso de autenticacion para obtener el id del usuario*/
        //log.info("Llega req -> " + id_user + " mas el body -> Cantidad" +addToCartDTO.getCantidad() +" ");
        carritoService.addToCarrito(id_user,addToCartDTO);
        return ResponseEntity.ok("Agregado al carrito!");
    }

    @GetMapping("/getCarritoAllProductsByIdUser/{id_user}")
    //este va a recibir el Authorization y en el le va a llegar el id del usuario, pero por ahora se lo pasamos nosotros
    public ResponseEntity<List<ProductDTO>> getAllProductosByIdUser(/*@Authorization String authorization*/@PathVariable Integer id_user){
        return ResponseEntity.ok(carritoService.getAllProductsByIdUser(id_user));
    }

    @DeleteMapping("/deleteFromCarrito/{id_user}/{id_producto}")
    public ResponseEntity<String> deleteFromCarrito(/*@Authorization String authorization*/@PathVariable Integer id_user,@PathVariable Integer id_producto){
        //proceso de autenticacion para obtener el id del usuario
        carritoService.deleteFromCarrito(id_user,id_producto);
        return ResponseEntity.ok("Producto eliminado del carrito!");
    }

    //quizas otra funcionalidad seria hacer un PUT por si el cliente quiere modificar un producto del carrito, por ejemplo bajarle la cantidad. Porque si ya lo guardo
    // en el carrito con una cantidad establecida, fue para to_do el viaje. Ej: guardo 2 cocacolas pero ahora quiere una sola en el carrito..blabla.
}
