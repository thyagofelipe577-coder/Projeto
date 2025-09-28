package com.floricultura.model.exceptions;

/**
 * Exceção lançada quando uma entidade não é encontrada.
 */
public class EntidadeNaoEncontradaException extends FloriculturaException {
    
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
    
    public EntidadeNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
