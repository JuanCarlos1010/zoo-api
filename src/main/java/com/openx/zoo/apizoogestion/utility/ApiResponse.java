package com.openx.zoo.apizoogestion.utility;

import lombok.Data;

@Data
public class ApiResponse <T>{
    private boolean completed;
    private String message;
    private T body;

    public ApiResponse(T body) {
        this.body = body;
        this.completed = true;
        this.message = "ok";
    }

    public ApiResponse(String message) {
        this.body = null;
        this.completed = false;
        this.message = message;
    }
}
