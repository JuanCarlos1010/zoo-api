package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.MedicineDTO;
import com.openx.zoo.api.entity.Medicine;
import org.springframework.stereotype.Component;

@Component
public class MedicineMapper extends AbstractMapper<Medicine, MedicineDTO> {

    @Override
    public MedicineDTO toDTO(Medicine medicine) {
        return MedicineDTO.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .expiredAt(medicine.getExpiredAt())
                .createdAt(medicine.getCreatedAt())
                .updatedAt(medicine.getUpdatedAt())
                .description(medicine.getDescription())
                .manufacturer(medicine.getManufacturer())
                .build();
    }

    @Override
    public Medicine toEntity(MedicineDTO medicine) {
        return Medicine.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .expiredAt(medicine.getExpiredAt())
                .description(medicine.getDescription())
                .manufacturer(medicine.getManufacturer())
                .build();
    }
}
