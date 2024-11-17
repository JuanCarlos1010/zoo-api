package com.openx.zoo.api.security;

public interface SecurityEnvs {
    String HTTP_HEADER_TOKEN = "Authorization";
    String JWT_PERMISSIONS_NAME = "permissions";
    String TOKEN_PREFIX = "Bearer ";
    String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    String PASSWORD_PATTERN = "^[a-zA-Z0-9@#$]+$";
}
