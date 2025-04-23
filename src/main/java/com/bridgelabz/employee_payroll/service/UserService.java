package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.LoginDto;
import com.bridgelabz.employee_payroll.dto.RegisterDto;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.User;
import com.bridgelabz.employee_payroll.repository.UserRepository;
import com.bridgelabz.employee_payroll.utility.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j // Lombok Logging
@Service
public class UserService implements UserInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    JwtUtility jwtUtility;

//    @Autowired
//    RabbitMQProducer rabbitMQProducer;

//    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public ResponseEntity<ResponseDTO> registerUser(RegisterDto registerDTO) {
        log.info("Registering user: {}", registerDTO.getEmail());
        if (existsByEmail(registerDTO.getEmail())) {
            log.warn("Registration failed: User already exists with email {}", registerDTO.getEmail());
            ResponseDTO response = new ResponseDTO("User Already Exists", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        User user = new User();
        user.setFullName(registerDTO.getFullName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        log.info("User {} registered successfully", user.getEmail());
        emailService.sendEmail(user.getEmail(), "Registered in Employee Payroll App", "Hi....\n You have been successfully registered!");

        ResponseDTO response = new ResponseDTO("User Registered Successfully", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> loginUser(LoginDto loginDTO) {
        log.info("Login attempt for user: {}", loginDTO.getEmail());
        Optional<User> userExists = getUserByEmail(loginDTO.getEmail());
        ResponseDTO response;

        if (userExists.isPresent()) {
            User user = userExists.get();
            if (matchPassword(loginDTO.getPassword(), user.getPassword())) {
                String token = jwtUtility.generateToken(user.getEmail());
                user.setToken(token);
                userRepository.save(user);

                log.debug("Login successful for user: {} - Token generated", user.getEmail());
                emailService.sendEmail(user.getEmail(), "Logged in Employee Payroll App", "Hi....\n You have been successfully logged in! " + token);

                response = new ResponseDTO("User Logged In Successfully:",token);
                return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
            } else {
                log.warn("Invalid credentials for user: {}", loginDTO.getEmail());
                response = new ResponseDTO("Invalid Credentials",null);
                return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
            }
        }

        log.error("User not found with email: {}", loginDTO.getEmail());
        response = new ResponseDTO("user not found",null);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @Override
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        log.debug("Matching password for login attempt");
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.debug("Checking if user exists by email: {}", email);
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        log.debug("Fetching user by email: {}", email);
        return userRepository.findByEmail(email);
    }
}