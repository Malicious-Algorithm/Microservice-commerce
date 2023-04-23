package com.microservice.cliente.controllers;

import com.microservice.cliente.models.Cliente;
import com.microservice.cliente.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/newCliente")
    public ResponseEntity<String> addNewCiente(@RequestBody Cliente cliente){
        clienteService.addCliente(cliente);
        return ResponseEntity.ok("Nuevo cliente agregado");
    }

    @GetMapping("/getClienteById/{id}")
    public ResponseEntity<Cliente> getClienteById(/*Authorization*/@PathVariable Integer id){
        //aca va la custom exception
        return ResponseEntity.ok(clienteService.getClienteById(id).orElseThrow());
    }

    @GetMapping("/getClienteByNombre/{nombreCliente}")
    public ResponseEntity<Cliente> getClienteById(/*Authorization*/@PathVariable String nombreCliente){
        //aca va la custom exception
        return ResponseEntity.ok(clienteService.getClienteByNombre(nombreCliente));
    }

    @GetMapping("/getClienteByEmail/{emailCliente}")
    public ResponseEntity<Cliente> getClienteByEmail(/*Authorization*/@PathVariable String emailCliente){
        //aca va la custom exception
        return ResponseEntity.ok(clienteService.getClienteByEmail(emailCliente));
    }

    @PutMapping("/updateClienteById/{id}")
    public ResponseEntity<String> updateCliente(/**/@PathVariable Integer id, @RequestBody Cliente cliente){
        clienteService.updateCliente(id,cliente);
        return ResponseEntity.ok("Cliente actualizado!");
    }
}
