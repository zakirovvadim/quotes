package com.example.cotirovki.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuoteGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<QuoteIncorrecData> handlerException(NoEntityException exception) {
        QuoteIncorrecData data = new QuoteIncorrecData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public  ResponseEntity<QuoteIncorrecData> handlerException(ValidationErrorException exception) {
        QuoteIncorrecData data = new QuoteIncorrecData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);
    }

}
