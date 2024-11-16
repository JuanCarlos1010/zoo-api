package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class RoleDTO {
    private long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
