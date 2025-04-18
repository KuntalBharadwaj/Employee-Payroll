package com.bridgelabz.employee_payroll.model;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeid;
    private String name;
    private double salary;

    public Employee(EmployeeDTO employeeDTO) {
        this.employeeid = employeeid;
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
    }

    public long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee() {
    }

    public Employee(int employeeid, String name, double salary) {
        this.employeeid = employeeid;
        this.name = name;
        this.salary = salary;
    }
}
