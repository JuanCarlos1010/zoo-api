package com.openx.zoo.api.service;

import com.openx.zoo.api.entity.Shopping;
import com.openx.zoo.api.entity.Supplier;
import com.openx.zoo.api.exception.BadRequestException;
import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.repository.ShoppingRepository;
import com.openx.zoo.api.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {
    private final ShoppingRepository shoppingRepository;
    private final SupplierRepository supplierRepository;

    public ShoppingService(ShoppingRepository shoppingRepository, SupplierRepository supplierRepository) {
        this.shoppingRepository = shoppingRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<Shopping> findAllShopping() {
        try {
            return shoppingRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }

    public Shopping getShoppingById(Long id) {
        return shoppingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Compra no encontrada en el id: " + id));
    }

    @Transactional
    public Shopping createShopping(Shopping shopping) {
        Supplier supplier = shopping.getSupplier();
        if (supplier == null) {
            throw new BadRequestException("El campo proveedor es obligatorio");
        } else {
            Optional<Supplier> supplierOpt = supplierRepository.findById(supplier.getId());
            if (supplierOpt.isEmpty()) {
                throw new NotFoundException("Proveedor no encontrado con id: " + supplier.getId());
            }
            shopping.setSupplier(supplierOpt.get());
            return shoppingRepository.save(shopping);
        }
    }

    @Transactional
    public Shopping updateShopping(Shopping updateShopping) {
        Supplier supplier = updateShopping.getSupplier();
        if (supplier == null) {
            throw new BadRequestException("El campo proveedor es obligatorio");
        }
        return shoppingRepository.findById(updateShopping.getId())
                .map(shopping -> {
                    Optional<Supplier> supplierOpt = supplierRepository.findById(supplier.getId());
                    if (supplierOpt.isEmpty()) {
                        throw new NotFoundException("Proveedor no encontrado con id: " + supplier.getId());
                    }
                    shopping.setTotal(updateShopping.getTotal());
                    shopping.setCreatedAt(updateShopping.getCreatedAt());
                    shopping.setSupplier(supplierOpt.get());
                    return shoppingRepository.save(shopping);
                })
                .orElseThrow(() -> new NotFoundException("Compra no encontrada con el id: " + updateShopping.getId()));
    }

    @Transactional
    public boolean deleteShopping(Long id) {
        Shopping shopping = getShoppingById(id);
        shoppingRepository.delete(shopping);
        return true;
    }
}
