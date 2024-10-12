package com.openx.zoo.apizoogestion.services;

import com.openx.zoo.apizoogestion.exceptions.NotFoundExeption;
import com.openx.zoo.apizoogestion.models.Employee;
import com.openx.zoo.apizoogestion.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository empleadoRepository) {
        this.employeeRepository = empleadoRepository;
    }

    public List<Employee> findAllEmployees(){
        try {
            return employeeRepository.findAll();
        } catch (Exception e){
            throw new NotFoundExeption(e);
        }
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(()-> new NotFoundExeption("Empleado no encontrado con el id: " + id));
    }

    @Transactional
    public Employee createEmployee(Employee employee){
        Optional<Employee> empleadoOpt = employeeRepository.findByName(employee.getName());
        if (empleadoOpt.isPresent()){
            throw new NotFoundExeption("Empleado con el nombre " + employee.getName() + " ya existe.");
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Employee employee){
        return employeeRepository.findById(employee.getId())
                .map(empleado -> {
                    empleado.setName(employee.getName());
                    empleado.setPosition(employee.getPosition());
                    empleado.setPhone(employee.getPhone());
                    empleado.setAddress(employee.getAddress());
                    empleado.setDescription(employee.getDescription());
                    return employeeRepository.save(empleado);
                })
                .orElseThrow(() -> new NotFoundExeption("Empleado no econtrado con id: " + employee.getId()));
    }

    @Transactional
    public boolean deleteEmployee(Long id){
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
        return true;
    }
}