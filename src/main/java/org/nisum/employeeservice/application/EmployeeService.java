package org.nisum.employeeservice.application;

import org.nisum.employeeservice.domain.model.Employee;
import org.nisum.employeeservice.interfaces.dto.EmployeeDTO;

public interface EmployeeService {
    Employee getEmployeeById(String id);
    void createEmployee(EmployeeDTO employeeDTO);
    void updateEmployee(Employee employee);
    void deleteEmployee(String id);
    // Other service methods
    void processEmployeeMessage(String message);
}
