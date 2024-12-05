package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class AnimalDTO {
    private long id;
    private long zoneId;
    private String age;
    private String name;
    private String gender;
    private String species;
    private String zoneName;
    private String description;
    private LocalDateTime birthdate;
    private LocalDateTime entryDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
