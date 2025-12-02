package com.projeto.projetospring.controller.exceptions;

import java.io.Serializable;
import java.io.Serial;

public class ErroCampo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

    public ErroCampo() {
    }

    public ErroCampo(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}