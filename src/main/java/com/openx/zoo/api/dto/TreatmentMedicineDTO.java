package com.openx.zoo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentMedicineDTO {
    private long id;
    private String dosage;
    private String comment;
    private long medicineId;
    private long treatmentId;
    private String medicineName;

    public TreatmentMedicineDTO(long id) {
        this.id = id;
    }
}
