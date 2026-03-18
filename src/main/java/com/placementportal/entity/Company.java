package com.placementportal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Double packageOffered;

    @Column
    private String location;

    @Column
    private Double cgpaCriteria;

    @Column
    private String eligibleBranches;

    @Column
    private LocalDate applicationDeadline;

    // ---- Constructors ----

    public Company() {}

    public Company(String companyName, String role, Double packageOffered, String location,
                   Double cgpaCriteria, String eligibleBranches, LocalDate applicationDeadline) {
        this.companyName = companyName;
        this.role = role;
        this.packageOffered = packageOffered;
        this.location = location;
        this.cgpaCriteria = cgpaCriteria;
        this.eligibleBranches = eligibleBranches;
        this.applicationDeadline = applicationDeadline;
    }

    // ---- Getters & Setters ----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getPackageOffered() {
        return packageOffered;
    }

    public void setPackageOffered(Double packageOffered) {
        this.packageOffered = packageOffered;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getCgpaCriteria() {
        return cgpaCriteria;
    }

    public void setCgpaCriteria(Double cgpaCriteria) {
        this.cgpaCriteria = cgpaCriteria;
    }

    public String getEligibleBranches() {
        return eligibleBranches;
    }

    public void setEligibleBranches(String eligibleBranches) {
        this.eligibleBranches = eligibleBranches;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }
}
