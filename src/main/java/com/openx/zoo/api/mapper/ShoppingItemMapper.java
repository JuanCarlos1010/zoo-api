package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.ShoppingItemDTO;
import com.openx.zoo.api.entity.Food;
import com.openx.zoo.api.entity.Shopping;
import com.openx.zoo.api.entity.ShoppingItem;
import org.springframework.stereotype.Component;

@Component
public class ShoppingItemMapper extends AbstractMapper<ShoppingItem, ShoppingItemDTO> {

    @Override
    public ShoppingItemDTO toDTO(ShoppingItem shoppingItem) {
        return ShoppingItemDTO.builder()
                .id(shoppingItem.getId())
                .price(shoppingItem.getPrice())
                .quantity(shoppingItem.getQuantity())
                .foodId(shoppingItem.getFood().getId())
                .foodName(shoppingItem.getFood().getName())
                .soppingId(shoppingItem.getShopping().getId())
                .build();
    }

    @Override
    public ShoppingItem toEntity(ShoppingItemDTO shoppingItem) {
        return ShoppingItem.builder()
                .id(shoppingItem.getId())
                .price(shoppingItem.getPrice())
                .quantity(shoppingItem.getQuantity())
                .food(new Food(shoppingItem.getFoodId()))
                .shopping(new Shopping(shoppingItem.getSoppingId()))
                .build();
    }
}
