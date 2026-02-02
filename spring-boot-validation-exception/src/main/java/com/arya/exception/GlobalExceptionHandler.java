package com.arya.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//AOP is a programming paradigm that separates cross-cutting concerns (like logging, security, transactions, exception handling) from business logic by applying behavior around method execution without modifying the method code itself
//controllerAdvice is like aop for global exception, data binding,..
@RestControllerAdvice
public class GlobalExceptionHandler {

    //handle validation errors
    // @ExceptionHandler
    // public ProblemDetail handleValidationErrors (MethodArgumentNotValidException ex){
    //     ProblemDetail prob = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
    //     prob.setTitle("Validation failed");
        
    // }
}
