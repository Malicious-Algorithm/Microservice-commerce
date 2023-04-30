package com.microservice.cliente.service;

import com.microservice.cliente.models.Cliente;
import com.microservice.cliente.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public String addCliente(Cliente cliente) {
        Cliente clienteOptional = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteOptional != null){
            return "Cliente existente";
        }else {
            clienteRepository.save(cliente);
            return "Usuario Agregado";
        }
    }

    @Override
    public Optional<Cliente> getClienteById(Integer id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()){
            Cliente clienteFound = clienteOptional.get();
            return Optional.of(clienteFound);
        }else
            return Optional.empty();
    }

    @Override
    public void updateCliente(Integer id, Cliente clienteRequest) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()){
            Cliente clienteExistente = clienteOptional.get();
            clienteExistente.setNombre(Objects.requireNonNullElse(clienteRequest.getNombre(),clienteExistente.getNombre()));
            clienteExistente.setApellido(Objects.requireNonNullElse(clienteRequest.getApellido(),clienteExistente.getApellido()));
            clienteExistente.setEmail(Objects.requireNonNullElse(clienteRequest.getEmail(),clienteExistente.getEmail()));
            //hacer un tratamiento para la password.
            clienteExistente.setPassword(Objects.requireNonNullElse(clienteRequest.getPassword(),clienteExistente.getPassword()));
            clienteRepository.save(clienteExistente);
        }
    }

    @Override
    public Cliente getClienteByNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    @Override
    public Cliente getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
}
