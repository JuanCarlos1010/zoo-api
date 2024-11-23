package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingItemDTO {
    private long id;
    private long foodId;
    private double price;
    private long soppingId;
    private double quantity;
    private String foodName;
}
