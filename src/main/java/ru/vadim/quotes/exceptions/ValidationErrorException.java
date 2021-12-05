package ru.vadim.quotes.exceptions;
/*Данное исключение выбрасывается при поступлении котировки с невалидными данными, т.е. длинною меньше 12 и bid большей ask*/
public class ValidationErrorException extends RuntimeException {
    public ValidationErrorException(String message) {
        super(message);
    }
}
