package com.openx.zoo.api.dto;

import com.openx.zoo.api.entities.Region;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ZoneDTO {
    private long id;
    private String name;
    private String type;
    private int capacity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Region region;
}
