package com.bridgelabz.employee_payroll.exceptions;

import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EmployeePayrollExceptionHandler {
    @ExceptionHandler(EmployeePayrollException.class)
    public static ResponseEntity<ResponseDTO> handleEmployeePayrollException(Exception exception) {
        ResponseDTO responseDTO = new ResponseDTO("Exception while proccesing request",exception.getMessage());
        System.out.println("hello from exception");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMap.put(error.getField(), error.getDefaultMessage())
        );

        ResponseDTO responseDTO = new ResponseDTO("Validation failed", errorMap);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
