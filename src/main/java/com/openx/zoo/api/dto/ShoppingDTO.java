package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ShoppingDTO {
    private long id;
    private double total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime supplyDate;
    private SupplierDTO supplier;
}