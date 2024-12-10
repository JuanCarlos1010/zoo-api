package com.openx.zoo.api.config;

import com.openx.zoo.api.exception.BadRequestException;
import com.openx.zoo.api.exception.ForbiddenException;
import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.exception.UnauthorizedException;
import com.openx.zoo.api.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(BadRequestException exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NotFoundException exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnauthorized(UnauthorizedException exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<Void>> handleForbidden(ForbiddenException exception) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponse);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ApiResponse<Void>> handleInternalError(InternalServerException exception) {
        exception.printStackTrace(System.err);
        ApiResponse<Void> apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception exception) {
        exception.printStackTrace(System.err);
        ApiResponse<Void> apiResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
