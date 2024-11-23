package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Builder
@Data
public class FoodDTO {
    private long id;
    private String name;
    private String type;
    private double stock;
    private String unitMeasurement;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
