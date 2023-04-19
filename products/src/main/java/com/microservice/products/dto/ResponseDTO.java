package com.microservice.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.products.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private Product producto;
    @JsonProperty("categoria")
    private CategoriaDTO categoriaDTO;
}
