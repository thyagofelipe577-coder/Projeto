package com.floricultura.model.exceptions;

/**
 * Exceção lançada quando não há estoque suficiente para uma operação.
 */
public class EstoqueInsuficienteException extends FloriculturaException {
    
    public EstoqueInsuficienteException(String message) {
        super(message);
    }
    
    public EstoqueInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }
}
