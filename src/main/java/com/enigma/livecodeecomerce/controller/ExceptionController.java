package com.enigma.livecodeecomerce.controller;

import com.enigma.livecodeecomerce.exception.FobiddenException;
import com.enigma.livecodeecomerce.exception.NotFoundException;
import com.enigma.livecodeecomerce.exception.UnautorizhedException;
import com.enigma.livecodeecomerce.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleRuntimeException(Exception e) {
        return ResponseEntity.internalServerError().body(new ExceptionResponse("500", e.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse("404", e.getMessage()));
    }

    @ExceptionHandler(FobiddenException.class)
    public ResponseEntity handleForbiddenException(FobiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse("403", e.getMessage()));
    }

    @ExceptionHandler(UnautorizhedException.class)
    public ResponseEntity handle(UnautorizhedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse("401", e.getMessage()));

    }
}
