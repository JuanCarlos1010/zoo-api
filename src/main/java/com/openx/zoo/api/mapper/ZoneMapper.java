package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.ZoneDTO;
import com.openx.zoo.api.entity.Region;
import com.openx.zoo.api.entity.Zone;
import org.springframework.stereotype.Component;

@Component
public class ZoneMapper extends AbstractMapper<Zone, ZoneDTO> {

    @Override
    public ZoneDTO toDTO(Zone zone) {
        return ZoneDTO.builder()
                .id(zone.getId())
                .name(zone.getName())
                .type(zone.getType())
                .capacity(zone.getCapacity())
                .createdAt(zone.getCreatedAt())
                .updatedAt(zone.getUpdatedAt())
                .regionId(zone.getRegion().getId())
                .regionName(zone.getRegion().getName())
                .build();
    }

    @Override
    public Zone toEntity(ZoneDTO zone) {
        return Zone.builder()
                .id(zone.getId())
                .name(zone.getName())
                .type(zone.getType())
                .capacity(zone.getCapacity())
                .createdAt(zone.getCreatedAt())
                .updatedAt(zone.getUpdatedAt())
                .region(new Region(zone.getRegionId()))
                .build();
    }
}
