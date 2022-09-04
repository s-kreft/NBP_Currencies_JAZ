package com.example.jaz_poprawa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ResponseException {
    @ExceptionHandler(HttpClientErrorException.class)
    ResponseEntity<String> HandlerRuntimeException(HttpClientErrorException exception) {
        return switch (exception.getStatusCode()) {
            case INTERNAL_SERVER_ERROR -> ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Błąd serwera - spróbuj ponownie później 500");
            case NOT_FOUND -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nie znaleziono - 404");
            case BAD_REQUEST -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nieprawidłowe żądanie - 400");
            case NO_CONTENT -> ResponseEntity.status(HttpStatus.NO_CONTENT).body("Utworzono. Brak zawartości 204");
            default -> ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Błąd: 502");
        };
    }

}