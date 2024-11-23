package com.openx.zoo.api.service;

import com.openx.zoo.api.exception.NotFoundException;
import com.openx.zoo.api.entity.Employee;
import com.openx.zoo.api.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado con el id: " + id));
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findByName(employee.getName());
        if (optionalEmployee.isPresent()) {
            throw new NotFoundException("Empleado con el nombre " + employee.getName() + " ya existe.");
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.findById(employee.getId())
                .map(item -> {
                    item.setName(employee.getName());
                    item.setPosition(employee.getPosition());
                    item.setPhone(employee.getPhone());
                    item.setAddress(employee.getAddress());
                    item.setDescription(employee.getDescription());
                    item.setUpdatedAt(employee.getUpdatedAt());
                    return employeeRepository.save(item);
                })
                .orElseThrow(() -> new NotFoundException("Empleado no econtrado con id: " + employee.getId()));
    }

    @Transactional
    public boolean deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
        return true;
    }
}