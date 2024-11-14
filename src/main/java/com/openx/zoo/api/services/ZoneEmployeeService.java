package com.openx.zoo.api.services;

import com.openx.zoo.api.exceptions.NotFoundException;
import com.openx.zoo.api.entities.*;
import com.openx.zoo.api.repositories.EmployeeRepository;
import com.openx.zoo.api.repositories.ZoneEmployeeRepository;
import com.openx.zoo.api.repositories.ZoneRepository;
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

    public ZoneEmployeeService(ZoneEmployeeRepository zoneEmployeeRepository, ZoneRepository zoneRepository, EmployeeRepository employeeRepository) {
        this.zoneEmployeeRepository = zoneEmployeeRepository;
        this.zoneRepository = zoneRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<ZoneEmployee> findAllZoneEmployees() {
        try {
            return zoneEmployeeRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }

    public ZoneEmployee getZoneEmployeeById(Long id) {
        return zoneEmployeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ZonaEmpleado no encontrado con id: " + id));
    }

    @Transactional
    public ZoneEmployee createZoneEmployee(ZoneEmployee zoneEmployee) {
        Zone zone = zoneEmployee.getZone();
        Employee employee = zoneEmployee.getEmployee();
        if ((zone == null) && (employee == null)) {
            throw new NotFoundException("El campo zona o empleado es obligatorio");
        } else {
            Optional<Zone> zoneOpt = zoneRepository.findById(zoneEmployee.getId());
            if (zoneOpt.isEmpty()) {
                assert zone != null;
                throw new NotFoundException("Zona no encontrado con id: " + zone.getId());
            } else {
                Optional<Employee> employeeOpt = employeeRepository.findById(zoneEmployee.getId());
                if (employeeOpt.isEmpty()) {
                    throw new NotFoundException("Empleado no encontrado con id: " + employee.getId());
                }
                zoneEmployee.setZone(zoneOpt.get());
                zoneEmployee.setEmployee(employeeOpt.get());
                return zoneEmployeeRepository.save(zoneEmployee);
            }
        }
    }

    @Transactional
    public ZoneEmployee updateZoneEmployee(ZoneEmployee createZoneEmployee) {
        Zone zone = createZoneEmployee.getZone();
        Employee employee = createZoneEmployee.getEmployee();
        if ((zone == null) && (employee == null)) {
            throw new NotFoundException("El campo zona o empleado es obligatorio");
        }
        return zoneEmployeeRepository.findById(createZoneEmployee.getId())
                .map(zoneEmployee -> {
                    zoneEmployee.setAssignmentDate(zoneEmployee.getAssignmentDate());
                    return zoneEmployeeRepository.save(zoneEmployee);
                })
                .orElseThrow(() -> new NotFoundException("ZonaEmpleado no encontrado con el id: " + createZoneEmployee.getId()));
    }

    @Transactional
    public boolean deleteZoneEmployee(Long id) {
        ZoneEmployee zoneEmployee = getZoneEmployeeById(id);
        zoneEmployeeRepository.delete(zoneEmployee);
        return true;
    }
}
