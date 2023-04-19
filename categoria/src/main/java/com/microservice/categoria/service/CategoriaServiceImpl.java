package com.microservice.categoria.service;

import com.microservice.categoria.models.Categoria;
import com.microservice.categoria.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Boolean addNuevaCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
        return true;
    }

    @Override
    public Optional<Categoria> getCategoriaById(Integer id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()){
            Categoria categoria = categoriaOptional.get();
            return Optional.of(categoria);
        }else
            return Optional.empty();
    }

    @Override
    public Boolean updateCategoriaById(Integer id, Categoria categoriaRequest) {
        Optional<Categoria> optionalCategoriaExist = getCategoriaById(id);
        if (optionalCategoriaExist.isPresent()){
            Categoria categoriaExistente = optionalCategoriaExist.get();
            categoriaExistente.setDescripcion(Objects.requireNonNullElse(categoriaRequest.getDescripcion(),categoriaExistente.getDescripcion()));
            categoriaExistente.setNombre(Objects.requireNonNullElse(categoriaRequest.getNombre(),categoriaExistente.getNombre()));
            categoriaExistente.setImagenUrl(Objects.requireNonNullElse(categoriaRequest.getImagenUrl(),categoriaExistente.getImagenUrl()));
            categoriaRepository.save(categoriaExistente);
            return true;
        }
        else
            return false;
    }

    @Override
    public void deleteCategoriaById(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
