package com.bridgelabz.employee_payroll;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EmployeePayrollApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EmployeePayrollApplication.class,args);
	}
}
