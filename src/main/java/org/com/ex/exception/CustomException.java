package org.com.ex.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final String reason;
    private final HttpStatus status;

    public CustomException(String reason, HttpStatus status) {
        super(reason);
        this.reason = reason;
        this.status = status;
    }
}
