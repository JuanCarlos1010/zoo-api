package com.openx.zoo.api.mapper;

import com.openx.zoo.api.dto.ZoneEmployeeDTO;
import com.openx.zoo.api.entity.Employee;
import com.openx.zoo.api.entity.Zone;
import com.openx.zoo.api.entity.ZoneEmployee;
import org.springframework.stereotype.Component;

@Component
public class ZoneEmployeeMapper extends AbstractMapper<ZoneEmployee, ZoneEmployeeDTO> {

    @Override
    public ZoneEmployeeDTO toDTO(ZoneEmployee zoneEmployee) {
        return ZoneEmployeeDTO.builder()
                .id(zoneEmployee.getId())
                .updatedAt(zoneEmployee.getUpdatedAt())
                .createdAt(zoneEmployee.getCreatedAt())
                .zoneId(zoneEmployee.getZone().getId())
                .zoneName(zoneEmployee.getZone().getName())
                .employeeId(zoneEmployee.getEmployee().getId())
                .assignmentDate(zoneEmployee.getAssignmentDate())
                .employeeName(zoneEmployee.getEmployee().getName())
                .build();
    }

    @Override
    public ZoneEmployee toEntity(ZoneEmployeeDTO zoneEmployee) {
        return ZoneEmployee.builder()
                .id(zoneEmployee.getId())
                .updatedAt(zoneEmployee.getUpdatedAt())
                .createdAt(zoneEmployee.getCreatedAt())
                .zone(new Zone(zoneEmployee.getZoneId()))
                .assignmentDate(zoneEmployee.getAssignmentDate())
                .employee(new Employee(zoneEmployee.getEmployeeId()))
                .build();
    }
}
