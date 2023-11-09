package org.dnsouzadev.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dnsouzadev.validation.NotEmptyList;

import java.math.BigDecimal;
import java.util.List;

/**
 * {
 *     "cliente": 1,
 *     "total": 100,
 *     "itemns": [
 *         {
 *             "produto": 1,
 *             "quantidade": 10
 *         }
 *     ]
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Informe o codigo do cliente")
    private Integer cliente;
    @NotNull(message = "campo total do pedido e obrigatorio")
    private BigDecimal total;
    @NotEmptyList(message = "A list nao pode ser vazia")
    private List<ItemPedidoDTO> items;
}
