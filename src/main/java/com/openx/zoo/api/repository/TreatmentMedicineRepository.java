package com.openx.zoo.api.repository;

import com.openx.zoo.api.entity.TreatmentMedicine;
import com.openx.zoo.api.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TreatmentMedicineRepository extends JpaRepository<TreatmentMedicine, Long> {
    List<TreatmentMedicine> findAllByTreatment(Treatment treatment);
}
