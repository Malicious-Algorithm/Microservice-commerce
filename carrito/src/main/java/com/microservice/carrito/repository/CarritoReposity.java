package com.microservice.carrito.repository;

import com.microservice.carrito.dto.CarritoDTO;
import com.microservice.carrito.models.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CarritoReposity extends JpaRepository<Carrito,Integer> {

    public List<Carrito> findByUserId(Integer user_id);


}
