package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.LoginDto;
import com.bridgelabz.employee_payroll.dto.RegisterDto;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserInterface {
    public ResponseEntity<ResponseDTO> registerUser(RegisterDto registerDTO);
    public ResponseEntity<ResponseDTO> loginUser(LoginDto loginDTO);
    public boolean matchPassword(String rawPassword, String encodedPassword);
    public boolean existsByEmail(String email);
    public Optional<User> getUserByEmail(String email);
}
