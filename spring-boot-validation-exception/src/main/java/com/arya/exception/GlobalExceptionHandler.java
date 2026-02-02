package com.arya.exception;

import java.util.HashMap;
import java.util.Map;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors (MethodArgumentNotValidException ex){
        ProblemDetail prob = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        prob.setTitle("Validation failed");
        
        //like a response container, key = field name
        //value = validation msg
        //binding is like request -> obj
        //getBindingResult is for getting a container from spring having some.. failures
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
            .getFieldErrors()
            .forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });

        prob.setProperty("errors", errors); //custom field
        return prob;
    }

    //handle businees logic errors, like id not found
    public ProblemDetail handleEmployeeNotFound(EmployeeNotFoundException ex){
        ProblemDetail prob = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        prob.setTitle("Resource not found...");
        prob.setDetail(ex.getMessage());
        return prob;
    }
}
