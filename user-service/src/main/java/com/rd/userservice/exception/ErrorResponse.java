package com.rd.userservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
    }
}
