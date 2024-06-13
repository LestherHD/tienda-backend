package com.tesis.tiendavirtualbackend.utils;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (isDuplicateKeyException(ex)) {
            return "Error: Clave duplicada. El recurso ya existe.";
        }
        return "Error: Violación de integridad de datos.";
    }

    private boolean isDuplicateKeyException(DataIntegrityViolationException ex) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) cause;
                // Código de error de clave duplicada en MySQL es 1062
                System.out.println("sqlEx.getErrorCode(): "+sqlEx.getErrorCode());
                if (sqlEx.getErrorCode() == 1062) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
}
