package com.microservice.authToken.repository;

import com.microservice.authToken.Dto.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Cliente, Integer> {
    public Cliente findByNombre(String nombre);

}
