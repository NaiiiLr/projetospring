package com.projeto.projetospring.service.exceptions;

public class ViolacaoIntegridadeDadosException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ViolacaoIntegridadeDadosException(String message) {
        super(message);
    }

    public ViolacaoIntegridadeDadosException(String message, Throwable cause) {
        super(message, cause);
    }
}