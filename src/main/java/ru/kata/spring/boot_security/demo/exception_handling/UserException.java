package ru.kata.spring.boot_security.demo.exception_handling;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
