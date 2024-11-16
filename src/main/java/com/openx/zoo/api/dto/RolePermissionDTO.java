package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class RolePermissionDTO {
    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private RoleDTO role;
    private PermissionDTO permission;
}
