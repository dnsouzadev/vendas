package org.dnsouzadev.service;

import org.dnsouzadev.domain.entity.Pedido;
import org.dnsouzadev.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
}
