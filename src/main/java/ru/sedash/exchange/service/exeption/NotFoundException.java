package ru.sedash.exchange.service.exeption;

public class NotFoundException extends ServiceException {

    public NotFoundException() {
        this("Request Not Found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
