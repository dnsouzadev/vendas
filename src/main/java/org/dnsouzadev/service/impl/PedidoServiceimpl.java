package org.dnsouzadev.service.impl;

import org.dnsouzadev.domain.repository.Pedidos;
import org.dnsouzadev.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceimpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceimpl(Pedidos repository) {
        this.repository = repository;
    }
}
