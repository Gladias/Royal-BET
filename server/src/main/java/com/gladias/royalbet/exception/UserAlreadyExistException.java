package com.gladias.royalbet.exception;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
