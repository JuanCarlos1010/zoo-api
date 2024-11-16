package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class PermissionDTO {
    private long id;
    private String name;
    private String description;
    private String moduleName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
