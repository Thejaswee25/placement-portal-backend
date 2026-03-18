package com.placementportal.dto;

import java.time.LocalDate;

public class ApplicationDTO {

    private Long id;
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String branch;
    private Double cgpa;
    private Long companyId;
    private String companyName;
    private String role;
    private String status;
    private LocalDate appliedDate;

    // ---- Constructors ----

    public ApplicationDTO() {}

    public ApplicationDTO(Long id, Long studentId, String studentName, String studentEmail,
                          String branch, Double cgpa, Long companyId, String companyName,
                          String role, String status, LocalDate appliedDate) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.branch = branch;
        this.cgpa = cgpa;
        this.companyId = companyId;
        this.companyName = companyName;
        this.role = role;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }
}
