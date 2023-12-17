package com.example.lab5.errors;

public class RowIsNotExistsException extends Exception {
    public static RowIsNotExistsException DEFAULT_INSTANCE = new RowIsNotExistsException("Row with this ID does not exist.");

    public RowIsNotExistsException(String message) {
        super(message);
    }
}
