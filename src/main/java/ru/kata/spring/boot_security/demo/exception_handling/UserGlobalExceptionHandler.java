package ru.kata.spring.boot_security.demo.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler
    ResponseEntity<UserIncorrectData> handleException (MethodArgumentNotValidException exception) {
        String message = exception.getMessage();
        UserIncorrectData data = new UserIncorrectData();
        if (message.contains("[roles]")) {
            data.setInfo("Необходимо выбрать роль!\n ");
        }
        if (message.contains("[email]")) {
            data.setInfo("Некорректный email!\n ");
        }
        if (message.contains("[age]")) {
            data.setInfo("Некорректной возраст!\n ");
        }
        if (message.contains("[surname]")) {
            data.setInfo("Некорректная фамилия!\n ");
        }
        if (message.contains("[username]")) {
            data.setInfo("Некорректное имя!\n ");
        }

        System.out.println(message);

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<UserIncorrectData> handleException (UserException exception) {
        System.out.println(exception.getMessage());
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
