package com.example.springwebtraining.error;

import com.example.springwebtraining.controller.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.example.springwebtraining.error.ErrorCode.GENERAL_VALIDATION_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(ApiException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getErrorCode().getCode(), ex.getErrorCode().getMessage()),
                ex.getErrorCode().getStatus()
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(GENERAL_VALIDATION_ERROR.getCode(), GENERAL_VALIDATION_ERROR.getMessage()),
                GENERAL_VALIDATION_ERROR.getStatus()
        );
    }
}
