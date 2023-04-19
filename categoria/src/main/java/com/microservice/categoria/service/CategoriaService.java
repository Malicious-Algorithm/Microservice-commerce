package com.microservice.categoria.service;

import com.microservice.categoria.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    public List<Categoria> getAllCategorias();
    public Boolean addNuevaCategoria(Categoria categoria);
    public Optional<Categoria> getCategoriaById(Integer id);
    public Boolean updateCategoriaById(Integer id, Categoria categoria);
    public void deleteCategoriaById(Integer id);
}
