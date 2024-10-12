package com.openx.zoo.apizoogestion.services;

import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.models.*;
import com.openx.zoo.apizoogestion.repositories.EmployeeRepository;
import com.openx.zoo.apizoogestion.repositories.ZoneEmployeeRepository;
import com.openx.zoo.apizoogestion.repositories.ZoneRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneEmployeeService {

    private final ZoneEmployeeRepository zoneEmployeeRepository;
    private final ZoneRepository zoneRepository;
    private final EmployeeRepository employeeRepository;

    public ZoneEmployeeService(ZoneEmployeeRepository zonaEmpleadoRepository, ZoneRepository zoneRepository, EmployeeRepository employeeRepository) {
        this.zoneEmployeeRepository = zonaEmpleadoRepository;
        this.zoneRepository = zoneRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<ZoneEmployee> findAllZoneEmployees(){
        try {
            return zoneEmployeeRepository.findAll();
        } catch (Exception e){
            throw new NotFoundExeption(e);
        }
    }

    public ZoneEmployee getZoneEmployeeById(Long id){
        return zoneEmployeeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("ZonaEmpleado no encontrado con id: " + id));
    }

    @Transactional
    public ZoneEmployee createZoneEmployee(ZoneEmployee zoneEmployee){
        Zone zone = zoneEmployee.getZone();
        Employee employee = zoneEmployee.getEmployee();
        if ((zone == null) && (employee == null)) {
            throw new NotFoundExeption("El campo zona o empleado es obligatorio");
        } else {
            Optional<Zone> zoneOpt = zoneRepository.findById(zoneEmployee.getId());
            if (zoneOpt.isEmpty()) {
                assert zone != null;
                throw new NotFoundExeption("Zona no encontrado con id: " + zone.getId());
            } else {
                Optional<Employee> employeeOpt = employeeRepository.findById(zoneEmployee.getId());
                if (employeeOpt.isEmpty()) {
                    throw new NotFoundExeption("Empleado no encontrado con id: " + employee.getId());
                }
                zoneEmployee.setZone(zoneOpt.get());
                zoneEmployee.setEmployee(employeeOpt.get());
                return zoneEmployeeRepository.save(zoneEmployee);
            }
        }
    }

    @Transactional
    public ZoneEmployee updateZoneEmployee(ZoneEmployee createZoneEmployee){
        Zone zone = createZoneEmployee.getZone();
        Employee employee = createZoneEmployee.getEmployee();
        if ((zone == null) && (employee == null)){
            throw new NotFoundExeption("El campo zona o empleado es obligatorio");
        }
        return zoneEmployeeRepository.findById(createZoneEmployee.getId())
                .map(zoneEmployee -> {
                    zoneEmployee.setAssignmentDate(zoneEmployee.getAssignmentDate());
                    return zoneEmployeeRepository.save(zoneEmployee);
                })
                .orElseThrow(() -> new NotFoundExeption("ZonaEmpleado no encontrado con el id: " + createZoneEmployee.getId()));
    }

    @Transactional
    public boolean deleteZoneEmployee(Long id){
        ZoneEmployee zoneEmployee = getZoneEmployeeById(id);
        zoneEmployeeRepository.delete(zoneEmployee);
        return true;
    }
}
