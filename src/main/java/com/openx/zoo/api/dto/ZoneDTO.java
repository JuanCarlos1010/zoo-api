package com.openx.zoo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneDTO {
    private long id;
    private String name;
    private int capacity;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RegionDTO region;

    public ZoneDTO(long id) {
        this.id = id;
    }
}
