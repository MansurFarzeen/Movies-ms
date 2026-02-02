package org.com.ex.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponseException> handleCustomException(CustomException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new CustomResponseException(ex.getReason() , ex.getStatus()));
    }
}

