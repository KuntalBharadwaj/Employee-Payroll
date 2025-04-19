package com.bridgelabz.employee_payroll.model;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeid;
    private String name;
    private double salary;
    private String gender;
    private LocalDate startdate;
    private String note;
    private String profilepic;
    private List<String> department;

    public Employee(EmployeeDTO empPayrollDTO) {
        this.name = empPayrollDTO.getName();
        this.salary = empPayrollDTO.getSalary();
        this.gender = empPayrollDTO.getGender();
        this.startdate = empPayrollDTO.getStartdate();
        this.note = empPayrollDTO.getNote();
        this.profilepic = empPayrollDTO.getProfilepic();
        this.department = empPayrollDTO.getDepartment();
    }
}
