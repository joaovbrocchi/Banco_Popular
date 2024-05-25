package com.joaovbrocchi.bancopopular.domain.exception;
import lombok.Data;

@Data
public class SemChavesDIsponiveisException extends RuntimeException {
    private String message;

    public SemChavesDIsponiveisException(String message) {
        this.message = message;
    }
}

