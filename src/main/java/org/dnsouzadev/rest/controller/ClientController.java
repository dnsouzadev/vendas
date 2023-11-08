package org.dnsouzadev.rest.controller;

import org.dnsouzadev.domain.entity.Cliente;
import org.dnsouzadev.domain.repository.Clientes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClientController {
    private final Clientes clientes;

    public ClientController( Clientes clientes ) {
        this.clientes = clientes;
    }

    

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById( @PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Cliente> save( @RequestBody Cliente cliente ) {
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete (@PathVariable Integer id ) {
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            clientes.delete( cliente.get() );
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
