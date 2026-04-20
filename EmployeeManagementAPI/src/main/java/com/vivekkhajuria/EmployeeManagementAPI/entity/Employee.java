package com.vivekkhajuria.EmployeeManagementAPI.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Columns;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "Department")
    private String dept;
}
