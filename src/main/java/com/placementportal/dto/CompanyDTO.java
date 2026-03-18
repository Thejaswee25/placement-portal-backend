package com.placementportal.dto;

import java.time.LocalDate;

public class CompanyDTO {

    private Long id;
    private String companyName;
    private String role;
    private Double packageOffered;
    private String location;
    private Double cgpaCriteria;
    private String eligibleBranches;
    private LocalDate applicationDeadline;

    // ---- Constructors ----

    public CompanyDTO() {}

    public CompanyDTO(Long id, String companyName, String role, Double packageOffered,
                      String location, Double cgpaCriteria, String eligibleBranches,
                      LocalDate applicationDeadline) {
        this.id = id;
        this.companyName = companyName;
        this.role = role;
        this.packageOffered = packageOffered;
        this.location = location;
        this.cgpaCriteria = cgpaCriteria;
        this.eligibleBranches = eligibleBranches;
        this.applicationDeadline = applicationDeadline;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Double getPackageOffered() { return packageOffered; }
    public void setPackageOffered(Double packageOffered) { this.packageOffered = packageOffered; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getCgpaCriteria() { return cgpaCriteria; }
    public void setCgpaCriteria(Double cgpaCriteria) { this.cgpaCriteria = cgpaCriteria; }

    public String getEligibleBranches() { return eligibleBranches; }
    public void setEligibleBranches(String eligibleBranches) { this.eligibleBranches = eligibleBranches; }

    public LocalDate getApplicationDeadline() { return applicationDeadline; }
    public void setApplicationDeadline(LocalDate applicationDeadline) { this.applicationDeadline = applicationDeadline; }
}
