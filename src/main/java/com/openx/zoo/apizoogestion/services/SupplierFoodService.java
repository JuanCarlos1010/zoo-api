package com.openx.zoo.apizoogestion.services;

import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.models.*;
import com.openx.zoo.apizoogestion.repositories.FoodRepository;
import com.openx.zoo.apizoogestion.repositories.SupplierFoodRepository;
import com.openx.zoo.apizoogestion.repositories.SupplierRepository;
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

    public List<SupplierFood> findAllSupplierFoods(){
        try {
            return supplierFoodRepository.findAll();
        } catch (Exception e){
            throw new NotFoundExeption(e);
        }
    }

    public SupplierFood getSupplierFoodById(Long id){
        return supplierFoodRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("ProveeedorAlimento no econtrado con id: " + id));
    }

    @Transactional
    public SupplierFood createSupplierFood(SupplierFood createSupplierFood){
        Supplier supplier = createSupplierFood.getSupplier();
        Food food = createSupplierFood.getFood();
        if ((supplier == null) && (food == null)) {
            throw new NotFoundExeption("El campo proveedor o alimento es obligatorio");
        } else {
            Optional<Supplier> supplierOpt = supplierRepository.findById(createSupplierFood.getId());
            if (supplierOpt.isEmpty()) {
                assert supplier != null;
                throw new NotFoundExeption("Proveedor no encontrado con id: " + supplier.getId());
            } else {
                Optional<Food> foodOpt = foodRepository.findById(createSupplierFood.getId());
                if (foodOpt.isEmpty()) {
                    throw new NotFoundExeption("Alimento no encontrado con id: " + food.getId());
                }
                createSupplierFood.setSupplier(supplierOpt.get());
                createSupplierFood.setFood(foodOpt.get());
                return supplierFoodRepository.save(createSupplierFood);
            }
        }
    }

    @Transactional
    public SupplierFood updateSupplierFood(SupplierFood updateSupplierFood){
        Supplier supplier = updateSupplierFood.getSupplier();
        Food food = updateSupplierFood.getFood();
        if ((supplier == null) && (food == null)){
            throw new NotFoundExeption("El campo proveedor o alimento es obligatorio");
        }
        return supplierFoodRepository.findById(updateSupplierFood.getId())
                .map(supplierFood -> {
                    supplierFood.setAmount(updateSupplierFood.getAmount());
                    return supplierFoodRepository.save(supplierFood);
                })
                .orElseThrow(() -> new NotFoundExeption("ProveedorAlimento no encontrado con el id: " + updateSupplierFood.getId()));
    }

    @Transactional
    public boolean deleteSupplierFood(Long id){
        SupplierFood supplierFood = getSupplierFoodById(id);
        supplierFoodRepository.delete(supplierFood);
        return true;
    }
}
