package org.dnsouzadev.service;

import org.dnsouzadev.domain.entity.Pedido;
import org.dnsouzadev.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto (Integer id);
}
