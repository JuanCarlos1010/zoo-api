package com.openx.zoo.api.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TreatmentDTO {
    private long id;
    private long animalId;
    private String comments;
    private String diagnosis;
    private String animalName;
    private long veterinarianId;
    private String veterinarianName;
    private LocalDateTime applyDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<TreatmentMedicineDTO> medicines;
}
