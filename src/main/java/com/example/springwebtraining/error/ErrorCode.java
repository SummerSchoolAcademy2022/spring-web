package com.example.springwebtraining.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNAUTHORIZED("UNAUTHORIZED", "Please login.", HttpStatus.UNAUTHORIZED),
    GENERAL_VALIDATION_ERROR("GENERAL_VALIDATION_ERROR", "The request is not valid.", HttpStatus.BAD_REQUEST),
    ;

    private String message;
    private String code;
    private HttpStatus status;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
