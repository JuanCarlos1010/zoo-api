package com.openx.zoo.api.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        String username,
        String fullName,
        String roleName,
        String token
) {}
