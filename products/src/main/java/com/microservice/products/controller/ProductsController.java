package com.microservice.products.controller;

import com.microservice.products.dto.ProductDTO;
import com.microservice.products.dto.ResponseDTO;
import com.microservice.products.models.Product;
import com.microservice.products.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@Slf4j
@RequestMapping("/products")
public class ProductsController {

    //TODO: agregar las customs Exceptions para cuando haga un {{api-products}}/productById/3 no me tire un 500 Internal Server Error, se ve horrible.
    @Autowired
    private ProductService productService;

    @GetMapping("/allProducts")
    public ResponseEntity<List<ResponseDTO>> getAllProducts(/*@Authorization String authorization */) throws Exception {
        //make a post for authorization token
        List<ResponseDTO> productDTO = productService.getAllProducts();
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("/addNewProduct")
    public ResponseEntity<String> addNewProducto(/*@Authorization String authorization*/@RequestBody Product product) {
        //make a post for authorization token
        productService.addProducto(product);
        return ResponseEntity.ok("Producto guardado.");
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<ResponseDTO> getProductById(/*@Authorization String authorization*/@PathVariable Integer id) throws Exception {
        ResponseDTO product = productService.getProductById(id).orElseThrow();
        return ResponseEntity.ok(product);
    }

    @PutMapping("/updateProductById/{id}")
    public ResponseEntity<Boolean> updateProduct(/*@Authorization String authorization*/@PathVariable Integer id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<Boolean> deleteProduct(/*@Authorization String authorization*/@PathVariable Integer id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
