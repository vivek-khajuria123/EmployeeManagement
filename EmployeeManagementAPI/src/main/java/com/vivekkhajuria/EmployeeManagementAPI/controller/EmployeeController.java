package com.vivekkhajuria.EmployeeManagementAPI.controller;

import com.vivekkhajuria.EmployeeManagementAPI.dto.EmployeeDto;
import com.vivekkhajuria.EmployeeManagementAPI.entity.Employee;
import com.vivekkhajuria.EmployeeManagementAPI.mapper.EmployeeMapper;
import com.vivekkhajuria.EmployeeManagementAPI.service.EmployeeService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/employeeManagement")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                                            @RequestParam(required = false, defaultValue = "5") int pageSize,
                                                            @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                            @RequestParam(required = false, defaultValue = "ASC") String sortDir,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String dept,
                                                            @RequestParam(required = false) String email,
                                                            @RequestParam(required = false) Long id){
        Sort sort;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortBy).ascending();
        } else{
            sort = Sort.by(sortBy).descending();
        }
        List<Employee> list = employeeService.getAllEmployees(PageRequest.of(pageNo-1, pageSize, sort), id, name, email, dept);
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
