package com.joaovbrocchi.bancopopular.domain.exception;

public class ChavePixException extends RuntimeException {
    private String message;

    public ChavePixException(String message) {
        super(message);
    }
}
