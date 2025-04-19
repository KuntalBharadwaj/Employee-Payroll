package com.bridgelabz.employee_payroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public @ToString class EmployeeDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",
            message = "Employee name Invalid")
    private String name;

    @Min(value = 500, message = "Min Wage should be more than 500")
    private double salary;

    @Pattern(regexp = "male|female", message = "Gender needs to be male or female")
    private String gender;

    @JsonFormat(pattern = "dd MM yyyy")
    @NotNull(message = "startdate should not be empty")
    @PastOrPresent(message = "startdate should be past or todays date")
    private LocalDate startdate;

    @NotBlank(message = "note should not be blank")
    private String note;

    @NotBlank(message = "profilepic can not be empty")
    private String profilepic;

    @NotNull(message = "department should not empty")
    private List<String> department;
}
