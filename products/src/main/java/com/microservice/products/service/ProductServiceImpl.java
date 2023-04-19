package com.microservice.products.service;

import com.microservice.products.dto.CategoriaDTO;
import com.microservice.products.dto.ResponseDTO;
import com.microservice.products.models.Product;
import com.microservice.products.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ResponseDTO> getAllProducts() throws Exception{
        try {
            List<Product> productList = productRepository.findAll();
            List<ResponseDTO> responseDTOList = new ArrayList<>();
            for (Product idProducts : productList) {
                ResponseDTO responseDTO = new ResponseDTO();
                CategoriaDTO respuesta = restTemplate.getForEntity("http://categoria:9092/categorias/categoriaById/" + idProducts.getCategoriaId(), CategoriaDTO.class).getBody();
                log.info("mircoservicio Categoria responde al endpoint categoriaById con: " + respuesta.toString());
                responseDTO.setProducto(idProducts);
                responseDTO.setCategoriaDTO(respuesta);
                responseDTOList.add(responseDTO);
            }
            return responseDTOList;
        }catch (Exception e){
            log.error("Hubo un problea en el microservicio, stack muestra error -> " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void addProducto(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<ResponseDTO> getProductById(Integer id) throws Exception {
        try{
            Optional<Product> productOpt = productRepository.findById(id);
            ResponseDTO responseDTO = new ResponseDTO();
            if(productOpt.isPresent()){
                Product productFound = productOpt.get();
                CategoriaDTO respuesta = restTemplate.getForEntity("http://categoria:9092/categorias/categoriaById/" + productFound.getCategoriaId(), CategoriaDTO.class).getBody();
                log.info("mircoservicio Categoria responde al endpoint categoriaById con: " + respuesta.toString());
                responseDTO.setProducto(productFound);
                responseDTO.setCategoriaDTO(respuesta);
                return Optional.of(responseDTO);
            }
            else
                return Optional.empty();
        }catch (Exception e){
            log.error("Hubo un problea en el microservicio, stack muestra error -> " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Boolean updateProduct(Integer id, Product productRequest) {
        Optional<Product> productFound = productRepository.findById(id);
        if(productFound.isPresent()) {
            Product product = productFound.get();
            product.setNombre(Objects.requireNonNullElse(productRequest.getNombre(),product.getNombre()));
            product.setPrecio(Objects.requireNonNullElse(productRequest.getPrecio(),product.getPrecio()));
            product.setDescripcion(Objects.requireNonNullElse(productRequest.getDescripcion(),product.getDescripcion()));
            product.setImagenUrl(Objects.requireNonNullElse(productRequest.getImagenUrl(),product.getImagenUrl()));
            product.setCategoriaId(Objects.requireNonNullElse(productRequest.getCategoriaId(),product.getCategoriaId()));
            productRepository.save(product);
            return true;
        }else
            return false;
    }

    @Override
    public Boolean deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return true; //que simpre retorne true incluso cuando no lo encuentre. mmm
    }
}
