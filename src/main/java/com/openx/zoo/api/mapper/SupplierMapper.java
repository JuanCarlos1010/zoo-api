package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.SupplierDTO;
import com.openx.zoo.api.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper extends AbstractMapper<Supplier, SupplierDTO> {

    @Override
    public SupplierDTO toDTO(Supplier supplier) {
        return SupplierDTO.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .address(supplier.getAddress())
                .createdAt(supplier.getCreatedAt())
                .updatedAt(supplier.getUpdatedAt())
                .build();
    }

    @Override
    public Supplier toEntity(SupplierDTO supplier) {
        return Supplier.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .address(supplier.getAddress())
                .createdAt(supplier.getCreatedAt())
                .updatedAt(supplier.getUpdatedAt())
                .build();
    }
}
