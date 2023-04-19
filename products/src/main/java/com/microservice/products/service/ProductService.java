package com.microservice.products.service;

import com.microservice.products.dto.ProductDTO;
import com.microservice.products.dto.ResponseDTO;
import com.microservice.products.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<ResponseDTO> getAllProducts()throws Exception;
    public void addProducto(Product product);
    public Optional<ResponseDTO> getProductById(Integer id) throws Exception;
    public Boolean updateProduct(Integer id, Product product);
    public Boolean deleteProduct(Integer id);

}
