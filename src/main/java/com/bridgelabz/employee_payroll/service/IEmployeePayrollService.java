package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.model.Employee;

import java.util.List;

public interface IEmployeePayrollService {
    public List<Employee> getEmployeePayrollData();
    public Employee getEmployeePayrollDataById(int empId);
    public Employee createEmployeePayrollData(EmployeeDTO empPayrollDTO);
    public Employee updateEmployeePayrollData(int empId, EmployeeDTO empPayrollDTO);
    public void deleteEmployeePayrollData(int empId);
}
