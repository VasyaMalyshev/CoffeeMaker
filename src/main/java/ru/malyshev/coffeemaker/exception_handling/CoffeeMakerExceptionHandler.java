package ru.malyshev.coffeemaker.controller.exception_handling;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoffeeMakerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Response> handleException()
}
