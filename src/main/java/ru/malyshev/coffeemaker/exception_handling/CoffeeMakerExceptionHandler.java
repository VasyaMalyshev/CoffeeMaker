package ru.malyshev.coffeemaker.exception_handling;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoffeeMakerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CoffeeMaker> handleException(CoffeeMakerException exception) {
        CoffeeMaker coffeeMaker = new CoffeeMaker();
        coffeeMaker.setInfo(exception.getMessage());
        return new ResponseEntity<>(coffeeMaker, HttpStatus.BAD_REQUEST);
    }
}
