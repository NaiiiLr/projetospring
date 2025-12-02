package com.projeto.projetospring.controller.exceptions;

import com.projeto.projetospring.service.exceptions.ObjetoNaoEncontradoException;
import com.projeto.projetospring.service.exceptions.ViolacaoIntegridadeDadosException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExcecao {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> objectNotFound(ObjetoNaoEncontradoException e, HttpServletRequest request) {

        ErroPadrao err = new ErroPadrao(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ViolacaoIntegridadeDadosException.class)
    public ResponseEntity<ErroPadrao> dataIntegrityViolation(ViolacaoIntegridadeDadosException e, HttpServletRequest request) {

        ErroPadrao err = new ErroPadrao(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Data Integrity Violation",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        ErroValidacao err = new ErroValidacao(
                System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation Error",
                "Erro na validação dos campos",
                request.getRequestURI());

        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }
}