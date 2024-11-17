package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionDTO {
    private long id;
    private String name;
    private String description;
    private String moduleName;
}
