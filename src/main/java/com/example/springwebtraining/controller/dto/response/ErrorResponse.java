package com.example.springwebtraining.controller.dto.response;

public record ErrorResponse(
    String code,
    String message
) { }
