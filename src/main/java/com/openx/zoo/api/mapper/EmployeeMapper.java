package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.EmployeeDTO;
import com.openx.zoo.api.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDTO> {

    @Override
    public EmployeeDTO toDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phone(employee.getPhone())
                .email(employee.getEmail())
                .address(employee.getAddress())
                .position(employee.getPosition())
                .description(employee.getDescription())
                .build();
    }

    @Override
    public Employee toEntity(EmployeeDTO employee) {
        return Employee.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phone(employee.getPhone())
                .email(employee.getEmail())
                .address(employee.getAddress())
                .position(employee.getPosition())
                .description(employee.getDescription())
                .build();
    }
}
