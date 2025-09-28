package com.floricultura.model.exceptions;

/**
 * Exceção lançada quando dados inválidos são fornecidos.
 */
public class DadosInvalidosException extends FloriculturaException {
    
    public DadosInvalidosException(String message) {
        super(message);
    }
    
    public DadosInvalidosException(String message, Throwable cause) {
        super(message, cause);
    }
}
