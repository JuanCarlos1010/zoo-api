package com.openx.zoo.api.mappers;

import com.openx.zoo.api.dto.ZoneEmployeeDTO;
import com.openx.zoo.api.entities.ZoneEmployee;
import org.springframework.stereotype.Component;

@Component
public class ZoneEmployeeMapper extends AbstractMapper<ZoneEmployee, ZoneEmployeeDTO> {
    private final ZoneMapper zoneMapper;
    private final EmployeeMapper employeeMapper;

    public ZoneEmployeeMapper(ZoneMapper zoneMapper, EmployeeMapper employeeMapper) {
        this.zoneMapper = zoneMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public ZoneEmployeeDTO toDTO(ZoneEmployee zoneEmployee) {
        return ZoneEmployeeDTO.builder()
                .id(zoneEmployee.getId())
                .updatedAt(zoneEmployee.getUpdatedAt())
                .createdAt(zoneEmployee.getCreatedAt())
                .zone(zoneMapper.toDTO(zoneEmployee.getZone()))
                .assignmentDate(zoneEmployee.getAssignmentDate())
                .employee(employeeMapper.toDTO(zoneEmployee.getEmployee()))
                .build();
    }

    @Override
    public ZoneEmployee toEntity(ZoneEmployeeDTO zoneEmployee) {
        return ZoneEmployee.builder()
                .id(zoneEmployee.getId())
                .updatedAt(zoneEmployee.getUpdatedAt())
                .createdAt(zoneEmployee.getCreatedAt())
                .assignmentDate(zoneEmployee.getAssignmentDate())
                .zone(zoneMapper.toEntity(zoneEmployee.getZone()))
                .employee(employeeMapper.toEntity(zoneEmployee.getEmployee()))
                .build();
    }
}
