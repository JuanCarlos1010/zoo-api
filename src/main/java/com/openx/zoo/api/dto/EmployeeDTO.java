package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class EmployeeDTO {
    private long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String position;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
