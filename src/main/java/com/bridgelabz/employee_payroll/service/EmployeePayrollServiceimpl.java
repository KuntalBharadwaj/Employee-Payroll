package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeePayrollServiceimpl implements IEmployeePayrollService {

    private List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> getEmployeePayrollData() {
        return employees;
    }

    public Employee getEmployeePayrollDataById(int empId) {
        return employees.get(empId-1);
    }

    public Employee createEmployeePayrollData(EmployeeDTO empPayrollDTO) {
        Employee employee = new Employee(employees.size()+1,empPayrollDTO);
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployeePayrollData(int empId, EmployeeDTO empPayrollDTO) {
        Employee empData = this.getEmployeePayrollDataById(empId);
        empData.setName(empPayrollDTO.getName());
        empData.setSalary(empPayrollDTO.getSalary());
        employees.set(empId-1,empData);
        return empData;
    }

    public void deleteEmployeePayrollData(int empId) {
        employees.remove(empId-1);
    }
}

