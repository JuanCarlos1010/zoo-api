package com.openx.zoo.api.repositories;

import com.openx.zoo.api.models.FoodAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FoodAnimalRepository extends JpaRepository<FoodAnimal, Long> {
    Optional<FoodAnimal> getByConsumptionDate(LocalDateTime consumptionDate);

//    @Query(
//            value = """
//                    SELECT aa.alimento FROM FoodAnimal aa
//                    WHERE aa.fechaConsumo BETWEEN :fechaInicio AND :fechaFinal
//                    AND aa.animal.id = :idAnimal
//                    """)
//    Collection<Food> getPorcionPorFecha(
//            @Param("fechaInicio") String fechaInicio,
//            @Param("fechaFinal") String fechaFinal,
//            @Param("idAnimal") Long idAnimal);
}
