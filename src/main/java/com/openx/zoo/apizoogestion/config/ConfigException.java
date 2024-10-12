package com.openx.zoo.apizoogestion.config;

import com.openx.zoo.apizoogestion.exceptions.BadRequestException;
import com.openx.zoo.apizoogestion.exceptions.InternalServerException;
import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ConfigException {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handlerBadRequest(BadRequestException exception){
        ApiResponse<Void> apiResponse = new ApiResponse<>(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(NotFoundExeption.class)
    public ResponseEntity<ApiResponse<Void>> handlerBadRequest(NotFoundExeption exception){
        ApiResponse<Void> apiResponse = new ApiResponse<>(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ApiResponse<Void>> handlerBadRequest(InternalServerException exception){
        ApiResponse<Void> apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
