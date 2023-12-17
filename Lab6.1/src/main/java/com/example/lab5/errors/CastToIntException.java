package com.example.lab5.errors;

public class CastToIntException extends Exception{
    public static CastToIntException DEFAULT_INSTANCE = new CastToIntException("Some fields values cannot be convert to integers");

    public CastToIntException(String message) {
        super(message);
    }
}
