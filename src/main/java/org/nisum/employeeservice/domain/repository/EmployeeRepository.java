package org.nisum.employeeservice.domain.repository;

import org.nisum.employeeservice.domain.model.Employee;

public interface EmployeeRepository {
    Employee findById(String id);
    void save(Employee employee);
    void delete(Employee employee);
    // Other repository methods
}
