package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.TreatmentMedicineDTO;
import com.openx.zoo.api.entity.TreatmentMedicine;
import com.openx.zoo.api.entity.Medicine;
import com.openx.zoo.api.entity.Treatment;
import org.springframework.stereotype.Component;

@Component
public class TreatmentMedicineMapper extends AbstractMapper<TreatmentMedicine, TreatmentMedicineDTO> {

    @Override
    public TreatmentMedicineDTO toDTO(TreatmentMedicine treatmentMedicine) {
        return TreatmentMedicineDTO.builder()
                .id(treatmentMedicine.getId())
                .dosage(treatmentMedicine.getDosage())
                .comment(treatmentMedicine.getComment())
                .medicineId(treatmentMedicine.getMedicine().getId())
                .treatmentId(treatmentMedicine.getTreatment().getId())
                .medicineName(treatmentMedicine.getMedicine().getName())
                .build();
    }

    @Override
    public TreatmentMedicine toEntity(TreatmentMedicineDTO animalMedicine) {
        return TreatmentMedicine.builder()
                .id(animalMedicine.getId())
                .dosage(animalMedicine.getDosage())
                .comment(animalMedicine.getComment())
                .medicine(new Medicine(animalMedicine.getMedicineId()))
                .treatment(new Treatment(animalMedicine.getTreatmentId()))
                .build();
    }
}
