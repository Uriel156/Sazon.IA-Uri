package com.Proyecto.SazonIA.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.Gson;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.PersistentObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private final Gson gson = new Gson();

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Resource not found");
        errorDetails.put("message", e.getMessage() != null ? e.getMessage() : "Recurso no encontrado");
        return new ResponseEntity<>(gson.toJson(errorDetails), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleJsonParseException(JsonParseException e) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Invalid JSON format");
        errorDetails.put("message", e.getOriginalMessage());
        return new ResponseEntity<>(gson.toJson(errorDetails), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Entity not found");
        errorDetails.put("message", e.getMessage());
        return new ResponseEntity<>(gson.toJson(errorDetails), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        e.getConstraintViolations().forEach(violation -> {
            message.append("Parámeter '")
                    .append(violation.getPropertyPath())
                    .append("' ")
                    .append(violation.getMessage())
                    .append(". \n");
        });
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Restriction Violation");
        errorDetails.put("message", message.toString());
        return new ResponseEntity<>(gson.toJson(errorDetails), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String message = e.getMessage();
        String detailedMessage = "Invalid JSON format";
        if (message != null) {
            if (message.contains("Unexpected character")) {
                detailedMessage = "Fill in the requested fields.";
            }
        }
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", detailedMessage);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Disallowed method");
        errorDetails.put("message", "The request method '" + e.getMethod() + "' no es compatible con este endpoint.");
        return new ResponseEntity<>(gson.toJson(errorDetails), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(PersistentObjectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlePersistentObjectException(PersistentObjectException e) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Persistent Object Exception");
        errorDetails.put("message", e.getMessage());
        return new ResponseEntity<>(gson.toJson(errorDetails), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleEmptyFieldException(EmptyFieldException e) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Campo vacío");
        errorDetails.put("message", e.getMessage());
        return new ResponseEntity<>(gson.toJson(errorDetails), HttpStatus.BAD_REQUEST);
    }

} 