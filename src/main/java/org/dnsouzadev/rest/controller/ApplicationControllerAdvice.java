package org.dnsouzadev.rest.controller;

import org.dnsouzadev.exception.PedidoNaoEncontradoException;
import org.dnsouzadev.exception.RegraNegocioException;
import org.dnsouzadev.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
        String messagemErro = ex.getMessage();
        return new ApiErrors(messagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException rc) {
        String mensagemErro = rc.getMessage();
        return new ApiErrors(mensagemErro);
    }
}
