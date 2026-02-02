package org.com.ex.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus status;

    public ApiException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
