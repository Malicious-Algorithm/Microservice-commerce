package com.microservice.cliente.service;

import com.microservice.cliente.models.Cliente;

import java.util.Optional;

public interface ClienteService {
    public void addCliente(Cliente cliente);
    public Optional<Cliente> getClienteById(Integer id);
    public void updateCliente(Integer id, Cliente cliente);

    public Cliente getClienteByNombre(String nombre);
    public Cliente getClienteByEmail(String email);
}
