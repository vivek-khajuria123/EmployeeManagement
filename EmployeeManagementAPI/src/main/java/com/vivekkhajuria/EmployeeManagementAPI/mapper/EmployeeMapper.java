package com.vivekkhajuria.EmployeeManagementAPI.mapper;

import com.vivekkhajuria.EmployeeManagementAPI.dto.EmployeeDto;
import com.vivekkhajuria.EmployeeManagementAPI.entity.Employee;

public class EmployeeMapper {
    public static Employee toEntity(EmployeeDto dto){
        Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setDept(dto.getDept());
        return emp;
    }

    public static EmployeeDto toDto(Employee employee){
        EmployeeDto empDto = new EmployeeDto();
        empDto.setId(employee.getId());
        empDto.setName(employee.getName());
        empDto.setEmail(employee.getEmail());
        empDto.setDept(employee.getDept());
        return empDto;
    }
}
