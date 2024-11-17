package com.openx.zoo.api.dto;

import lombok.Builder;

@Builder
public record SignInRequest(
        String username,
        String password
) {}
