package ru.vadim.quotes.exceptions;

public class NoEntityException extends RuntimeException {
    public NoEntityException(String message) {
        super(message);
    }
}
