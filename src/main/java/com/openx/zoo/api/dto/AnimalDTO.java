package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class AnimalDTO {
    private long id;
    private String name;
    private LocalDateTime birthdate;
    private LocalDateTime entryDate;
    private String gender;
    private String species;
    private int age;
    private ZoneDTO zone;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
