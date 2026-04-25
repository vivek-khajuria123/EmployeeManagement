package com.vivekkhajuria.EmployeeManagementAPI.service.impl;

import com.vivekkhajuria.EmployeeManagementAPI.entity.Employee;
import com.vivekkhajuria.EmployeeManagementAPI.repository.EmployeeRepository;
import com.vivekkhajuria.EmployeeManagementAPI.service.EmployeeService;
import com.vivekkhajuria.EmployeeManagementAPI.specification.EmployeeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees(Pageable pageable, Long id, String name, String email, String dept) {
        Specification<Employee> specification = EmployeeSpecification.getSpecification(id, name, email, dept);
        return employeeRepository.findAll(specification,pageable).getContent();
    }


    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        Employee existing = getEmployeeById(id);
        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDept(employee.getDept());
        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
