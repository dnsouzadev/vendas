package org.dnsouzadev.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException() {
        super("Pedido Nao Encontrado");
    }
}
