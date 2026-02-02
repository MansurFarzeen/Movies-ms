package org.com.ex.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomResponseException {
    private String reason;
    private HttpStatus status;
}
