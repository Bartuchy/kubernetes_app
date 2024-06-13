package com.example.backend.common.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private List<String> details;
}
