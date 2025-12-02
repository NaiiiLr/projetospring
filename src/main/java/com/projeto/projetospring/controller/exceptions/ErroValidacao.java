package com.projeto.projetospring.controller.exceptions;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadrao {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<ErroCampo> errors = new ArrayList<>();

    public ErroValidacao() {
        super();
    }

    public ErroValidacao(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<ErroCampo> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new ErroCampo(fieldName, message));
    }
}