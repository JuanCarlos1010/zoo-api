package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.TreatmentDTO;
import com.openx.zoo.api.entity.Animal;
import com.openx.zoo.api.entity.Treatment;
import com.openx.zoo.api.entity.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class TreatmentMapper extends AbstractMapper<Treatment, TreatmentDTO> {
    private final TreatmentMedicineMapper mapper;

    public TreatmentMapper(TreatmentMedicineMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TreatmentDTO toDTO(Treatment treatment) {
        return TreatmentDTO.builder()
                .id(treatment.getId())
                .comments(treatment.getComments())
                .applyDate(treatment.getApplyDate())
                .createdAt(treatment.getCreatedAt())
                .updatedAt(treatment.getUpdatedAt())
                .diagnosis(treatment.getDiagnosis())
                .animalId(treatment.getAnimal().getId())
                .animalName(treatment.getAnimal().getName())
                .veterinarianId(treatment.getVeterinarian().getId())
                .veterinarianName(treatment.getVeterinarian().getFullName())
                .medicines(treatment.getMedicines() != null ? mapper.toDTO(treatment.getMedicines()) : new ArrayList<>(0))
                .build();
    }

    @Override
    public Treatment toEntity(TreatmentDTO treatment) {
        return Treatment.builder()
                .id(treatment.getId())
                .comments(treatment.getComments())
                .diagnosis(treatment.getDiagnosis())
                .applyDate(treatment.getApplyDate())
                .animal(new Animal(treatment.getAnimalId()))
                .veterinarian(new User(treatment.getVeterinarianId()))
                .medicines(treatment.getMedicines() != null ? mapper.toEntity(treatment.getMedicines()) : new ArrayList<>(0))
                .build();
    }
}
