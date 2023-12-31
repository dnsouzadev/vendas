package org.dnsouzadev.service;

import org.dnsouzadev.domain.entity.Pedido;
import org.dnsouzadev.domain.enuns.StatusPedido;
import org.dnsouzadev.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto (Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
