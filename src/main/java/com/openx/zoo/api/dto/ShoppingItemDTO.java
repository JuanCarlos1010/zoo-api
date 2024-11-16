package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingItemDTO {
    private long id;
    private double price;
    private double quantity;
    private FoodDTO food;
    private ShoppingDTO shopping;
}
