package com.microservice.cliente.repository;

import com.microservice.cliente.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    public Cliente findByNombre(String nombre);
    public Cliente findByEmail(String email);

}
