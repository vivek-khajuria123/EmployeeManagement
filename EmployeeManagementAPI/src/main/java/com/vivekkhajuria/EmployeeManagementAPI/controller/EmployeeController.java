package com.vivekkhajuria.EmployeeManagementAPI.controller;

import com.vivekkhajuria.EmployeeManagementAPI.dto.EmployeeDto;
import com.vivekkhajuria.EmployeeManagementAPI.entity.Employee;
import com.vivekkhajuria.EmployeeManagementAPI.mapper.EmployeeMapper;
import com.vivekkhajuria.EmployeeManagementAPI.service.EmployeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employeeManagement")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<Employee> list = employeeService.getAllEmployees();
        List<EmployeeDto> listDto = list.stream().map(EmployeeMapper::toDto).toList();
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id){
        Employee saveEmp = employeeService.getEmployeeById(id);
        EmployeeDto saveEmpDto = EmployeeMapper.toDto(saveEmp);
        return ResponseEntity.ok(saveEmpDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        Employee emp = EmployeeMapper.toEntity(employeeDto);
        Employee saveEmp = employeeService.createEmployee(emp);
        EmployeeDto empDto = EmployeeMapper.toDto(saveEmp);
        return ResponseEntity.status(201).body(empDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto){
        Employee emp = EmployeeMapper.toEntity(employeeDto);
        Employee saveEmp = employeeService.updateEmployee(id,emp);
        EmployeeDto updatedEmp = EmployeeMapper.toDto(saveEmp);
        return ResponseEntity.status(201).body(updatedEmp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
