package com.floricultura.model.exceptions;

/**
 * Exceção base para o sistema de floricultura.
 * Todas as exceções específicas do sistema devem herdar desta classe.
 */
public class FloriculturaException extends Exception {
    
    public FloriculturaException(String message) {
        super(message);
    }
    
    public FloriculturaException(String message, Throwable cause) {
        super(message, cause);
    }
}
