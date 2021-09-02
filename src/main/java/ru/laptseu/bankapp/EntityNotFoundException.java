package ru.laptseu.bankapp;

public class EntityNotFoundException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
