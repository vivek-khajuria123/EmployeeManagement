package com.vivekkhajuria.EmployeeManagementAPI.repository;

import com.vivekkhajuria.EmployeeManagementAPI.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor {
    Page<Employee> findByName(String name, Pageable pageable);
}
