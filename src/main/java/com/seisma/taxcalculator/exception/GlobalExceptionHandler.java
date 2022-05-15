package com.seisma.taxcalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomError> handleConstraintViolationException(final ConstraintViolationException exception) {
        ConstraintViolation violation = exception.getConstraintViolations()
                .stream().findFirst().orElse(null);

        String errorMsg = errorMsg = String.format("%s %s",
                violation.getPropertyPath().toString(),
                violation.getMessage());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(HttpStatus.BAD_REQUEST.toString(), errorMsg));
    }
}
