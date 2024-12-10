package com.openx.zoo.api.service;

import com.openx.zoo.api.entity.TreatmentMedicine;
import com.openx.zoo.api.entity.Treatment;
import com.openx.zoo.api.entity.Medicine;
import com.openx.zoo.api.exception.BadRequestException;
import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.repository.TreatmentMedicineRepository;
import com.openx.zoo.api.repository.TreatmentRepository;
import com.openx.zoo.api.repository.MedicineRepository;
import com.openx.zoo.api.utils.ExcelMedicHistory;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {
    private final MedicineRepository medicineRepository;
    private final TreatmentRepository treatmentRepository;
    private final TreatmentMedicineRepository treatmentMedicineRepository;

    public TreatmentService(MedicineRepository medicineRepository, TreatmentRepository treatmentRepository, TreatmentMedicineRepository treatmentMedicineRepository) {
        this.medicineRepository = medicineRepository;
        this.treatmentRepository = treatmentRepository;
        this.treatmentMedicineRepository = treatmentMedicineRepository;
    }

    public List<Medicine> findMedicines() {
        return medicineRepository.findAllByDeletedAtIsNull();
    }

    @Transactional
    public Medicine createMedicine(Medicine medicine) {
        try {
            if(medicine.getName() == null)
                throw new BadRequestException("Nombre del medicamento es campo obligatorio");
            medicine.setCreatedAt(LocalDateTime.now());
            return medicineRepository.save(medicine);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    @Transactional
    public Medicine updateMedicine(Medicine updateMedicine) {
        try {
            return medicineRepository.findById(updateMedicine.getId())
                    .map(vaccine -> {
                        if(updateMedicine.getName() == null)
                            throw new BadRequestException("Nombre del medicamento es campo obligatorio");

                        vaccine.setName(updateMedicine.getName());
                        vaccine.setUpdatedAt(LocalDateTime.now());
                        vaccine.setDescription(updateMedicine.getDescription());
                        return medicineRepository.save(vaccine);
                    })
                    .orElseThrow(() -> new NotFoundException("Medicamento no encontrado"));
        } catch (BadRequestException | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    @Transactional
    public boolean deleteMedicine(Long id) {
        try {
            Medicine deleted = medicineRepository.findById(id)
                    .map(vaccine -> {
                        vaccine.setDeletedAt(LocalDateTime.now());
                        return medicineRepository.save(vaccine);
                    })
                    .orElseThrow(() -> new NotFoundException("Medicamento no encontrado"));
            return deleted != null;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    public List<Treatment> findTreatments() {
        return treatmentRepository.findAllByDeletedAtIsNull();
    }

    @Transactional
    public Treatment createTreatment(Treatment treatment) {
        try {
            if(treatment.getAnimal() == null || treatment.getVeterinarian() == null)
                throw new BadRequestException("Los campos Animal y Veterinario son obligatorios");
            treatment.setCreatedAt(LocalDateTime.now());
            Treatment response = treatmentRepository.save(treatment);

            List<TreatmentMedicine> medicines = treatment.getMedicines().parallelStream()
                    .peek(treatmentMedicine -> treatmentMedicine.setTreatment(response)).toList();
            treatmentMedicineRepository.saveAll(medicines);
            response.setMedicines(null);
            return response;
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    @Transactional
    public Treatment updateTreatment(Treatment updateTreatment) {
        try {
            Treatment response = treatmentRepository.findById(updateTreatment.getId())
                    .map(treatment -> {
                        if(updateTreatment.getAnimal() == null || updateTreatment.getVeterinarian() == null)
                            throw new BadRequestException("Los campos Animal y Veterinario son obligatorios");

                        treatment.setUpdatedAt(LocalDateTime.now());
                        treatment.setAnimal(updateTreatment.getAnimal());
                        treatment.setComments(updateTreatment.getComments());
                        treatment.setApplyDate(updateTreatment.getApplyDate());
                        treatment.setDiagnosis(updateTreatment.getDiagnosis());
                        return treatmentRepository.save(treatment);
                    })
                    .orElseThrow(() -> new NotFoundException("Historial médico no encontrado"));

            List<TreatmentMedicine> treatmentMedicines = treatmentMedicineRepository.findAllByTreatment(response);
            treatmentMedicineRepository.deleteAll(treatmentMedicines);
            List<TreatmentMedicine> medicines = updateTreatment.getMedicines()
                    .parallelStream()
                    .peek(treatmentMedicine -> {
                         treatmentMedicine.setTreatment(response);
                    }).toList();
            treatmentMedicineRepository.saveAll(medicines);
            response.setMedicines(null);
            return response;
        } catch (BadRequestException | NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    @Transactional
    public boolean deleteTreatment(long id) {
        try {
            Treatment deleted = treatmentRepository.findById(id)
                    .map(treatment -> {
                        treatment.setDeletedAt(LocalDateTime.now());
                        return treatmentRepository.save(treatment);
                    })
                    .orElseThrow(() -> new NotFoundException("Tratamiendo médico no encontrado"));
            return deleted != null;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    public byte[] download() {
        try {
            List<Treatment> treatments = findTreatments();
            return new ExcelMedicHistory().downloadMedicalHistories(treatments);
        } catch (NotFoundException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }
}
