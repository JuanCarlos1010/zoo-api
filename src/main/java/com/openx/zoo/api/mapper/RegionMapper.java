package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.RegionDTO;
import com.openx.zoo.api.entity.Region;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper extends AbstractMapper<Region, RegionDTO> {

    @Override
    public RegionDTO toDTO(Region region) {
        return RegionDTO.builder()
                .id(region.getId())
                .name(region.getName())
                .createdAt(region.getCreatedAt())
                .updatedAt(region.getUpdatedAt())
                .description(region.getDescription())
                .build();
    }

    @Override
    public Region toEntity(RegionDTO regionDTO) {
        return Region.builder()
                .id(regionDTO.getId())
                .name(regionDTO.getName())
                .createdAt(regionDTO.getCreatedAt())
                .updatedAt(regionDTO.getUpdatedAt())
                .description(regionDTO.getDescription())
                .build();
    }
}