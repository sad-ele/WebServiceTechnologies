package com.example.lab5.errors;

public class EmptyFieldException extends Exception {
    public static EmptyFieldException DEFAULT_INSTANCE = new EmptyFieldException("Some fields cannot be null or empty");

    public EmptyFieldException(String message) {
        super(message);
    }
}
