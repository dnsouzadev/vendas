package org.dnsouzadev.rest.controller;

import jakarta.validation.Valid;
import org.dnsouzadev.domain.entity.ItemPedido;
import org.dnsouzadev.domain.entity.Pedido;
import org.dnsouzadev.domain.enuns.StatusPedido;
import org.dnsouzadev.rest.dto.AtualizacaoStatusPedidoDTO;
import org.dnsouzadev.rest.dto.InformacaoItemPedidoDTO;
import org.dnsouzadev.rest.dto.InformacoesPedidoDTO;
import org.dnsouzadev.rest.dto.PedidoDTO;
import org.dnsouzadev.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service
                .obterPedidoCompleto(id)
                .map(this::converter)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Pedido Nao Encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus( @RequestBody AtualizacaoStatusPedidoDTO dto ,
                              @PathVariable Integer id) {
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));

    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens
                .stream()
                .map( item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .quantidade(item.getQuantidade())
                        .precoUnitario(item.getProduto().getPreco())
                        .build()
                ).collect(Collectors.toList());
    }
}
