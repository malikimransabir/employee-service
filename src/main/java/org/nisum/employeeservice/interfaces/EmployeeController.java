package org.nisum.employeeservice.interfaces;

import org.nisum.employeeservice.domain.model.Employee;
import org.nisum.employeeservice.application.EmployeeService;
import org.nisum.employeeservice.interfaces.dto.EmployeeRequest;
import org.nisum.employeeservice.mapper.EmployeeConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeRequest request) {
        employeeService.createEmployee(EmployeeConverter.toDTO(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable String id, @RequestBody EmployeeRequest request) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            employee.setName(request.getName());
            employee.setEmail(request.getEmail());
            employeeService.updateEmployee(employee);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
    // Other controller methods
}

