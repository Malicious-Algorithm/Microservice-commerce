package com.microservice.categoria.controller;

import com.microservice.categoria.models.Categoria;
import com.microservice.categoria.service.CategoriaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/allCategorias")
    private ResponseEntity<List<Categoria>> getAllCategorias(/*@RequestParam String authorization*/){
        //proceso de autorizacion
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }

    @PostMapping("/addCategoria")
    private ResponseEntity<Boolean> addCategoria(/*@RequestParam String authorization*/@RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaService.addNuevaCategoria(categoria));
    }

    @GetMapping("/categoriaById/{id}")
    private ResponseEntity<Categoria> getOneCategoria(/*@RequestParam String authorization*/@PathVariable Integer id){
        //proceso de autorizacion
        return ResponseEntity.ok(categoriaService.getCategoriaById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro esa categoria!")));
    }

    @PutMapping("/modificarCategoria/{id}")
    private ResponseEntity<Boolean> updateCategoriaById(/*@RequestParam String authorization*/@PathVariable Integer id, @RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaService.updateCategoriaById(id,categoria));
    }

    @DeleteMapping("/eliminarCategoriaById/{id}")
    private ResponseEntity<String> deleteCategoriaById(/*@RequestParam String authorization*/@PathVariable Integer id){
        categoriaService.deleteCategoriaById(id);
        return ResponseEntity.ok("Categoria eliminada.");
    }

}
