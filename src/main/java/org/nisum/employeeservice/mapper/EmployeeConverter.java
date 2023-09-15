package org.nisum.employeeservice.mapper;

import org.nisum.employeeservice.domain.model.Employee;
import org.nisum.employeeservice.interfaces.dto.EmployeeDTO;
import org.nisum.employeeservice.interfaces.dto.EmployeeRequest;

public class EmployeeConverter {
    public static EmployeeDTO toDTO(Employee employee) {
        // Convert Employee to EmployeeDTO
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        // ... map other properties
        return dto;
    }

    public static Employee toEntity(EmployeeDTO dto) {
        // Convert EmployeeDTO to Employee
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        // ... map other properties
        return employee;
    }

    public static EmployeeDTO toDTO(EmployeeRequest request) {
        // Convert Employee to EmployeeDTO
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(request.getName());
        // ... map other properties
        return dto;
    }
}
