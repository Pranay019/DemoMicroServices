package com.demo.profile.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {



    // Handle invalid input (e.g., validation errors)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("errors", ex.getBindingResult().getFieldErrors().stream()
                .collect(HashMap::new,
                        (m, fieldError) -> m.put(fieldError.getField(), fieldError.getDefaultMessage()),
                        HashMap::putAll));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle type mismatch (e.g., passing string instead of int)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", "Invalid type for parameter: " + ex.getName());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeException(RuntimeException ex){
    	 Map<String, Object> error = new HashMap<>();
         error.put("timestamp", LocalDateTime.now());
         error.put("message", ex.getMessage());
         error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    	return  new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
