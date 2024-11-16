package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ZoneEmployeeDTO {
    private long id;
    private LocalDateTime assignmentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ShoppinDTO zone;
    private EmployeeDTO employee;
}
