package ru.vadim.quotes.exceptions;
/*Данное исключение выбрасывается при отсуствии запрашиваемой записи объекта из базы данных*/
public class NoEntityException extends RuntimeException {
    public NoEntityException(String message) {
        super(message);
    }
}
