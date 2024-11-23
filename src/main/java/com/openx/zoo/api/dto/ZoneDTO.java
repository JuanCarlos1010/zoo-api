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
    private String type;
    private int capacity;
    private long regionId;
    private String regionName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ZoneDTO(long id) {
        this.id = id;
    }
}
