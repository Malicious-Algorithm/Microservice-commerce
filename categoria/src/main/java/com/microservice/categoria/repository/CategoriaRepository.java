package com.microservice.categoria.repository;

import com.microservice.categoria.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

//aca va @Repository y deberiamos cambiar en la Main Class el @EnableJpaRepositories para que apunte a este file
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
