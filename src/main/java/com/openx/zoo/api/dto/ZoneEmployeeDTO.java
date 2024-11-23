package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ZoneEmployeeDTO {
    private long id;
    private long zoneId;
    private String zoneName;
    private long employeeId;
    private String employeeName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime assignmentDate;
}
