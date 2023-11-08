package org.dnsouzadev.rest.controller;


import org.dnsouzadev.domain.entity.Cliente;
import org.dnsouzadev.domain.entity.Produto;
import org.dnsouzadev.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final Produtos produtos;

    public ProdutoController(Produtos produtos) {
        this.produtos = produtos;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> find(Produto filtro ) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Produto> example = Example.of(filtro, matcher);
        List<Produto> lista = produtos.findAll(example);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {
        Optional<Produto> produto = produtos.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {
        Produto produtoSalvo = produtos.save(produto);
        return ResponseEntity.ok(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto produto) {
        Optional<Produto> produtoAntigo = produtos.findById(id);
        if (produtoAntigo.isPresent()) {
            produto.setId(id);
            produtos.save(produto);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable Integer id) {
        Optional<Produto> produtoAntigo = produtos.findById(id);
        if (produtoAntigo.isPresent()) {
            produtos.delete( produtoAntigo.get() );
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
