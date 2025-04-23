package com.bridgelabz.employee_payroll.repository;

import com.bridgelabz.employee_payroll.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
