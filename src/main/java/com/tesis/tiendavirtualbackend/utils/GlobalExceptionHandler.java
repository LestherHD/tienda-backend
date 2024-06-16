package com.tesis.tiendavirtualbackend.utils;

import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;

public class GlobalExceptionHandler {

    public static String handleDataIntegrityViolationException(DataIntegrityViolationException ex, HashMap<String, String> mapaExcepciones) {
        if (isDuplicateKeyOrReferenceException(ex)) {
            String errorMessage = ex.getMessage();
            if (errorMessage.contains("Duplicate entry")) {

                if (mapaExcepciones != null && !mapaExcepciones.isEmpty()){
                    for (String key : mapaExcepciones.keySet()) {
                        String value = mapaExcepciones.get(key);
                        if (errorMessage.contains(key)){
                            return "Error, el valor del campo "+ value + " ya se encuentra registrado.";
                        }
                    }
                }
            } else if (errorMessage.contains("Cannot delete or update a parent row: a foreign key constraint fails")){
                if (mapaExcepciones != null && !mapaExcepciones.isEmpty()){
                    for (String key : mapaExcepciones.keySet()) {
                        String value = mapaExcepciones.get(key);
                        if (errorMessage.contains(key)){
                            return "Error, no se puede eliminar el registro seleccionado ya que se encuentra asociado a "+value+".";
                        }
                    }
                }
            }
        }
        return "";
    }

    private static boolean isDuplicateKeyOrReferenceException(DataIntegrityViolationException ex) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) cause;
                if (sqlEx.getErrorCode() == 1062 || sqlEx.getErrorCode() == 1451) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
}
