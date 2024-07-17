package com.teste.api.config;

import com.teste.api.exception.ApiException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map> handleDuplicateDataException(
            ApiException exception) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", exception.getMessage());
        responseBody.put("status_code", String.valueOf(exception.getStatusCode()));
        return new ResponseEntity<>(responseBody, HttpStatusCode.valueOf(exception.getStatusCode()));
    }
}
