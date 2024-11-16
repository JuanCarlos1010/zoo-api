package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dtos.RegionDTO;
import com.openx.zoo.api.entities.Region;
import org.springframework.stereotype.Service;

@Service
public class RegionConverter extends AbstractMapper<Region, RegionDTO> {

    @Override
    public RegionDTO toDTO(Region region) {
        if (region == null) return null;
        return RegionDTO.builder()
                .id(region.getId())
                .name(region.getName())
                .createdAt(region.getCreatedAt())
                .updatedAt(region.getUpdatedAt())
                .build();
    }

    @Override
    public Region toEntity(RegionDTO regionDTO) {
        if (regionDTO == null) return null;
        return Region.builder()
                .id(regionDTO.getId())
                .name(regionDTO.getName())
                .createdAt(regionDTO.getCreatedAt())
                .updatedAt(regionDTO.getUpdatedAt())
                .build();
    }
}