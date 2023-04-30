package com.microservice.carrito.controllers;

import com.microservice.carrito.dto.CarritoDTO;
import com.microservice.carrito.models.Carrito;
import com.microservice.carrito.payload.request.AddToCartDTO;
import com.microservice.carrito.payload.response.ProductDTO;
import com.microservice.carrito.service.AuthorizationService;
import com.microservice.carrito.service.CarritoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.carrito.payload.response.ResponseAuthorization;


import java.util.List;

@RestController
@RequestMapping("/carrito")
@Slf4j
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/addToCarrito")
    public ResponseEntity<String> addToCarrito(@RequestHeader("Authorization") String authorizationReq,@RequestBody AddToCartDTO addToCartDTO) throws Exception {
        ResponseAuthorization idUserFromToken = authorizationService.authorization(authorizationReq);
        carritoService.addToCarrito(idUserFromToken.getId(),addToCartDTO,authorizationReq);
        return ResponseEntity.ok("Agregado al carrito!");
    }

    @GetMapping("/getCarritoAllProductsByIdUser")
    public ResponseEntity<List<ProductDTO>> getAllProductosByIdUser(@RequestHeader("Authorization") String authorizationReq) throws Exception {
        ResponseAuthorization idUserFromToken = authorizationService.authorization(authorizationReq);
        return ResponseEntity.ok(carritoService.getAllProductsByIdUser(idUserFromToken.getId(),authorizationReq));
    }

    @DeleteMapping("/deleteFromCarrito/{id_producto}")
    public ResponseEntity<String> deleteFromCarrito(@RequestHeader("Authorization") String authorizationReq,@PathVariable Integer id_producto) throws Exception {
        ResponseAuthorization idUserFromToken = authorizationService.authorization(authorizationReq);
        carritoService.deleteFromCarrito(idUserFromToken.getId(),id_producto,authorizationReq);
        return ResponseEntity.ok("Producto eliminado del carrito!");
    }

    //quizas otra funcionalidad seria hacer un PUT por si el cliente quiere modificar un producto del carrito, por ejemplo bajarle la cantidad. Porque si ya lo guardo
    // en el carrito con una cantidad establecida, fue para to_do el viaje. Ej: guardo 2 cocacolas pero ahora quiere una sola en el carrito..blabla.
}
