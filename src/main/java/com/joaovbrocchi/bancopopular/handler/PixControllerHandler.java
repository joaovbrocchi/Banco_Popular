package com.joaovbrocchi.bancopopular.handler;

import com.joaovbrocchi.bancopopular.domain.exception.ChavePixException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PixControllerHandler {
    @ExceptionHandler(ChavePixException.class)
    public ResponseEntity<?> handleChavePixException(ChavePixException e){
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
}
