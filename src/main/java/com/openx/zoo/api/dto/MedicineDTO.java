package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class MedicineDTO {
    private long id;
    private String name;
    private String description;
    private String manufacturer;
    private LocalDate expiredAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
