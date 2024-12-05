package com.openx.zoo.api.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        long userId,
        String username,
        String fullName,
        String roleName,
        String token
) {}
