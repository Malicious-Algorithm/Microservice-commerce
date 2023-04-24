package com.microservice.products.controller;

import com.microservice.products.dto.ResponseDTO;
import com.microservice.products.models.Product;
import com.microservice.products.service.AuthorizationService;
import com.microservice.products.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("/allProducts")
    public ResponseEntity<List<ResponseDTO>> getAllProducts(@RequestHeader("Authorization") String authorizationReq) throws Exception {
        authorizationService.authorization(authorizationReq);
        List<ResponseDTO> productDTO = productService.getAllProducts();
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("/addNewProduct")
    public ResponseEntity<String> addNewProducto(@RequestBody Product product) {
        productService.addProducto(product);
        return ResponseEntity.ok("Producto guardado.");
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<ResponseDTO> getProductById(@RequestHeader("Authorization") String authorizationReq,@PathVariable Integer id) throws Exception {
        authorizationService.authorization(authorizationReq);
        ResponseDTO product = productService.getProductById(id).orElseThrow();
        return ResponseEntity.ok(product);
    }

    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<Boolean> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
