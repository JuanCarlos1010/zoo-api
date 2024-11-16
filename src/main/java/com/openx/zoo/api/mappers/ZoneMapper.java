package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.ZoneDTO;
import com.openx.zoo.api.entities.Zone;
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
                .build();
    }
}
