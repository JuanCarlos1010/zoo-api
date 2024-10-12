package com.openx.zoo.apizoogestion.services;

import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.models.Supplier;
import com.openx.zoo.apizoogestion.repositories.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> findAllSuppliers(){
        try {
            return supplierRepository.findAll();
        } catch (Exception e){
            throw new NotFoundExeption(e);
        }
    }

    public Supplier getSupplierById(Long id){
        return supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundExeption("Proveedor no existe con el id: " + id));
    }

    @Transactional
    public Supplier createSupplier(Supplier supplier){
        Optional<Supplier> supplierOpt = supplierRepository.getByName(supplier.getName());
        if (supplierOpt.isPresent()){
            throw new IllegalArgumentException("Proveedor con el nombre " + supplier.getName() + " ya existe");
        }
        return supplierRepository.save(supplier);
    }

    @Transactional
    public Supplier updateSupplier(Supplier updateSupplier){
        return supplierRepository.findById(updateSupplier.getId())
                .map(supplier -> {
                    supplier.setName(updateSupplier.getName());
                    supplier.setMail(updateSupplier.getMail());
                    supplier.setPhone(updateSupplier.getPhone());;
                    supplier.setAddress(updateSupplier.getAddress());
                    return supplierRepository.save(supplier);
                })
                .orElseThrow((() -> new NotFoundExeption("Proveedor no encontrado con el id: " + updateSupplier.getId())));
    }

    @Transactional
    public boolean deleteSupplier(Long id){
        Supplier supplier = getSupplierById(id);
        supplierRepository.delete(supplier);
        return true;
    }
}
