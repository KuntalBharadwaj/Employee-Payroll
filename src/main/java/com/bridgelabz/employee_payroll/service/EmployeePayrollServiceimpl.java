package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.exceptions.EmployeePayrollException;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class EmployeePayrollServiceimpl implements IEmployeePayrollService {

    private List<Employee> employees = new ArrayList<>();

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployeePayrollData() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeePayrollDataById(int empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeePayrollException("Employee Not Found"));
    }

    public Employee createEmployeePayrollData(EmployeeDTO empPayrollDTO) {
        try {
            Employee employee = employee = new Employee(empPayrollDTO);
            employeeRepository.save(employee);
            log.info("User Successfully created");
            return employee;
        } catch (Exception e) {
            throw new EmployeePayrollException(e.getMessage());
        }
    }

    public Employee updateEmployeePayrollData(int empId, EmployeeDTO empPayrollDTO) {
        try {
            Employee empData = this.getEmployeePayrollDataById(empId);
            empData.setName(empPayrollDTO.getName());
            empData.setSalary(empPayrollDTO.getSalary());
            employeeRepository.save(empData);
            log.info("User Successfully update");
            return empData;
        } catch (Exception e) {
            throw new EmployeePayrollException(e.getMessage());
        }
    }

    public void deleteEmployeePayrollData(int empId) {
        try {
            if(!employeeRepository.existsById(empId)) throw new EmployeePayrollException("Provided id is not found");
            employeeRepository.deleteById(empId);
            log.info("User Successfully deleted");
        } catch (Exception e) {
            throw new EmployeePayrollException(e.getMessage());
        }
    }
}

