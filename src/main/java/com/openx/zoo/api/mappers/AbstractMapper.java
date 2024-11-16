package com.openx.zoo.api.mappers;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<ENTITY, DTO> {
    public abstract DTO toDTO(ENTITY entity);
    public abstract ENTITY toEntity(DTO dto);

    public List<DTO> toDTO(List<ENTITY> entities){
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ENTITY> toEntity(List<DTO> dtos){
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}