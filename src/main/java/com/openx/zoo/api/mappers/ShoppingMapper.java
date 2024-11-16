package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.ShoppingDTO;
import com.openx.zoo.api.entities.Shopping;
import org.springframework.stereotype.Component;

@Component
public class ShoppingMapper extends AbstractMapper<Shopping, ShoppingDTO> {
    private final SupplierMapper supplierMapper;

    public ShoppingMapper(SupplierMapper supplierMapper) {
        this.supplierMapper = supplierMapper;
    }

    @Override
    public ShoppingDTO toDTO(Shopping shopping) {
        return ShoppingDTO.builder()
                .id(shopping.getId())
                .total(shopping.getTotal())
                .createdAt(shopping.getCreatedAt())
                .updatedAt(shopping.getUpdatedAt())
                .supplyDate(shopping.getSupplyDate())
                .supplier(supplierMapper.toDTO(shopping.getSupplier()))
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
                .supplier(supplierMapper.toEntity(shopping.getSupplier()))
                .build();
    }
}
