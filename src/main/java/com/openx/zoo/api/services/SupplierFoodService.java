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

    public List<ShoppingItem> findAllSupplierFoods() {
        try {
            return supplierFoodRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }

    public ShoppingItem getSupplierFoodById(Long id) {
        return supplierFoodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProveeedorAlimento no econtrado con id: " + id));
    }

    @Transactional
    public ShoppingItem createSupplierFood(ShoppingItem createShoppingItem) {
        Supplier supplier = createShoppingItem.getSupplier();
        Food food = createShoppingItem.getFood();
        if ((supplier == null) && (food == null)) {
            throw new NotFoundException("El campo proveedor o alimento es obligatorio");
        } else {
            Optional<Supplier> supplierOpt = supplierRepository.findById(createShoppingItem.getId());
            if (supplierOpt.isEmpty()) {
                assert supplier != null;
                throw new NotFoundException("Proveedor no encontrado con id: " + supplier.getId());
            } else {
                Optional<Food> foodOpt = foodRepository.findById(createShoppingItem.getId());
                if (foodOpt.isEmpty()) {
                    throw new NotFoundException("Alimento no encontrado con id: " + food.getId());
                }
                createShoppingItem.setSupplier(supplierOpt.get());
                createShoppingItem.setFood(foodOpt.get());
                return supplierFoodRepository.save(createShoppingItem);
            }
        }
    }

    @Transactional
    public ShoppingItem updateSupplierFood(ShoppingItem updateShoppingItem) {
        Supplier supplier = updateShoppingItem.getSupplier();
        Food food = updateShoppingItem.getFood();
        if ((supplier == null) && (food == null)) {
            throw new NotFoundException("El campo proveedor o alimento es obligatorio");
        }
        return supplierFoodRepository.findById(updateShoppingItem.getId())
                .map(shoppingItem -> {
                    shoppingItem.setAmount(updateShoppingItem.getAmount());
                    return supplierFoodRepository.save(shoppingItem);
                })
                .orElseThrow(() -> new NotFoundException("ProveedorAlimento no encontrado con el id: " + updateShoppingItem.getId()));
    }

    @Transactional
    public boolean deleteSupplierFood(Long id) {
        ShoppingItem shoppingItem = getSupplierFoodById(id);
        supplierFoodRepository.delete(shoppingItem);
        return true;
    }
}
