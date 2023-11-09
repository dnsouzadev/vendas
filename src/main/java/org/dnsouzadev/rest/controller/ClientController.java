package org.dnsouzadev.rest.controller;

import jakarta.validation.Valid;
import org.dnsouzadev.domain.entity.Cliente;
import org.dnsouzadev.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {
    private final Clientes clientes;

    public ClientController( Clientes clientes ) {
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById( @PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> save( @RequestBody @Valid Cliente cliente ) {
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id ) {
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            clientes.delete( cliente.get() );
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update (@PathVariable Integer id, @RequestBody Cliente cliente ) {
        Optional<Cliente> clienteAntigo = clientes.findById(id);
        if (clienteAntigo.isPresent()){
            cliente.setId(id);
            clientes.save(cliente);

            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> find( Cliente filtro ) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example<Cliente> example = Example.of(filtro, matcher);
        List<Cliente> lista = clientes.findAll(example);
        return ResponseEntity.ok(lista);
    }
}
