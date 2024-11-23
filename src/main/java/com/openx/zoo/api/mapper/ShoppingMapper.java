package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.ShoppingDTO;
import com.openx.zoo.api.entity.Shopping;
import com.openx.zoo.api.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class ShoppingMapper extends AbstractMapper<Shopping, ShoppingDTO> {

    @Override
    public ShoppingDTO toDTO(Shopping shopping) {
        return ShoppingDTO.builder()
                .id(shopping.getId())
                .total(shopping.getTotal())
                .createdAt(shopping.getCreatedAt())
                .updatedAt(shopping.getUpdatedAt())
                .supplyDate(shopping.getSupplyDate())
                .supplierId(shopping.getSupplier().getId())
                .supplierName(shopping.getSupplier().getName())
                .build();
    }

    @Override
    public Shopping toEntity(ShoppingDTO shopping) {
        return Shopping.builder()
                .id(shopping.getId())
                .total(shopping.getTotal())
                .createdAt(shopping.getCreatedAt())
                .updatedAt(shopping.getUpdatedAt())
                .supplyDate(shopping.getSupplyDate())
                .supplier(new Supplier(shopping.getSupplierId()))
                .build();
    }
}
