package ru.vadim.quotes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuoteGlobalExceptionHandler {

    /**

     * @param exception

     * @return

     */
    @ExceptionHandler(NoEntityException.class)
    public ResponseEntity<QuoteErrorData> handlerException(NoEntityException exception) {
        QuoteErrorData data = new QuoteErrorData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
    }

    /**

     * @param exception

     * @return

     */
    @ExceptionHandler(ValidationErrorException.class)
    public  ResponseEntity<QuoteErrorData> handlerException(ValidationErrorException exception) {
        QuoteErrorData data = new QuoteErrorData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);
    }

}
