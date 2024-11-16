package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.ShoppingItemDTO;
import com.openx.zoo.api.entities.ShoppingItem;
import org.springframework.stereotype.Component;

@Component
public class ShoppingItemMapper extends AbstractMapper<ShoppingItem, ShoppingItemDTO> {
    private final FoodMapper foodMapper;
    private final ShoppingMapper shoppingMapper;

    public ShoppingItemMapper(FoodMapper foodMapper, ShoppingMapper shoppingMapper) {
        this.foodMapper = foodMapper;
        this.shoppingMapper = shoppingMapper;
    }

    @Override
    public ShoppingItemDTO toDTO(ShoppingItem shoppingItem) {
        return ShoppingItemDTO.builder()
                .id(shoppingItem.getId())
                .price(shoppingItem.getPrice())
                .quantity(shoppingItem.getQuantity())
                .food(foodMapper.toDTO(shoppingItem.getFood()))
                .shopping(shoppingMapper.toDTO(shoppingItem.getShopping()))
                .build();
    }

    @Override
    public ShoppingItem toEntity(ShoppingItemDTO shoppingItem) {
        return ShoppingItem.builder()
                .id(shoppingItem.getId())
                .price(shoppingItem.getPrice())
                .quantity(shoppingItem.getQuantity())
                .food(foodMapper.toEntity(shoppingItem.getFood()))
                .shopping(shoppingMapper.toEntity(shoppingItem.getShopping()))
                .build();
    }
}
