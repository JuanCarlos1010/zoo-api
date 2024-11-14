package com.openx.zoo.api.services;

import com.openx.zoo.api.exceptions.NotFoundException;
import com.openx.zoo.api.entities.*;
import com.openx.zoo.api.repositories.FoodRepository;
import com.openx.zoo.api.repositories.SupplierFoodRepository;
import com.openx.zoo.api.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierFoodService {
    private final SupplierFoodRepository supplierFoodRepository;
    private final SupplierRepository supplierRepository;
    private final FoodRepository foodRepository;

    public SupplierFoodService(SupplierFoodRepository supplierFoodRepository, SupplierRepository supplierRepository, FoodRepository foodRepository) {
        this.supplierFoodRepository = supplierFoodRepository;
        this.supplierRepository = supplierRepository;
        this.foodRepository = foodRepository;
    }

    public List<SupplierFood> findAllSupplierFoods() {
        try {
            return supplierFoodRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }

    public SupplierFood getSupplierFoodById(Long id) {
        return supplierFoodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProveeedorAlimento no econtrado con id: " + id));
    }

    @Transactional
    public SupplierFood createSupplierFood(SupplierFood createSupplierFood) {
        Supplier supplier = createSupplierFood.getSupplier();
        Food food = createSupplierFood.getFood();
        if ((supplier == null) && (food == null)) {
            throw new NotFoundException("El campo proveedor o alimento es obligatorio");
        } else {
            Optional<Supplier> supplierOpt = supplierRepository.findById(createSupplierFood.getId());
            if (supplierOpt.isEmpty()) {
                assert supplier != null;
                throw new NotFoundException("Proveedor no encontrado con id: " + supplier.getId());
            } else {
                Optional<Food> foodOpt = foodRepository.findById(createSupplierFood.getId());
                if (foodOpt.isEmpty()) {
                    throw new NotFoundException("Alimento no encontrado con id: " + food.getId());
                }
                createSupplierFood.setSupplier(supplierOpt.get());
                createSupplierFood.setFood(foodOpt.get());
                return supplierFoodRepository.save(createSupplierFood);
            }
        }
    }

    @Transactional
    public SupplierFood updateSupplierFood(SupplierFood updateSupplierFood) {
        Supplier supplier = updateSupplierFood.getSupplier();
        Food food = updateSupplierFood.getFood();
        if ((supplier == null) && (food == null)) {
            throw new NotFoundException("El campo proveedor o alimento es obligatorio");
        }
        return supplierFoodRepository.findById(updateSupplierFood.getId())
                .map(supplierFood -> {
                    supplierFood.setAmount(updateSupplierFood.getAmount());
                    return supplierFoodRepository.save(supplierFood);
                })
                .orElseThrow(() -> new NotFoundException("ProveedorAlimento no encontrado con el id: " + updateSupplierFood.getId()));
    }

    @Transactional
    public boolean deleteSupplierFood(Long id) {
        SupplierFood supplierFood = getSupplierFoodById(id);
        supplierFoodRepository.delete(supplierFood);
        return true;
    }
}
