package com.example.demo.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EmpDto {

    private Integer empId;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "[A-Za-z]{3,25}", message = "Name must be alphabets, 3 to 25 characters")
    private String empName;

    @NotNull(message = "Salary is required")
    @Min(value = 1000, message = "Salary must be minimum 1000")
    @Max(value = 500000, message = "Salary must be maximum 500000")
    private Double empSal;

    @NotNull(message = "Date of joining required")
    @FutureOrPresent(message = "Date must be current or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate empDoj;

    @Pattern(regexp = "hr|production", message = "Department must be hr or production")
    private String deptName;

    public EmpDto() {
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getEmpSal() {
        return empSal;
    }

    public void setEmpSal(Double empSal) {
        this.empSal = empSal;
    }

    public LocalDate getEmpDoj() {
        return empDoj;
    }

    public void setEmpDoj(LocalDate empDoj) {
        this.empDoj = empDoj;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}