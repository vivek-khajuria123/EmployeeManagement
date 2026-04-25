package com.vivekkhajuria.EmployeeManagementAPI.service;

import com.vivekkhajuria.EmployeeManagementAPI.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees(Pageable pageable, Long id, String name, String email, String dept);
    Employee getEmployeeById(Integer id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Integer id, Employee employee);
    void deleteEmployee(Integer id);
}
