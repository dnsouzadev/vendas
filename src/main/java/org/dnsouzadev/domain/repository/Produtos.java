package org.dnsouzadev.domain.repository;

import org.dnsouzadev.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
}