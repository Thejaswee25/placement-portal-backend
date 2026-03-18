package com.placementportal.dto;

import java.util.List;

public class StudentProfileDTO {

    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String branch;
    private Double cgpa;
    private Integer graduationYear;
    private String phone;
    private String linkedin;
    private String github;
    private String resumeUrl;
    private List<String> skills;

    // ---- Constructors ----

    public StudentProfileDTO() {}

    public StudentProfileDTO(Long id, Long userId, String name, String email, String branch,
                              Double cgpa, Integer graduationYear, String phone,
                              String linkedin, String github, String resumeUrl, List<String> skills) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.cgpa = cgpa;
        this.graduationYear = graduationYear;
        this.phone = phone;
        this.linkedin = linkedin;
        this.github = github;
        this.resumeUrl = resumeUrl;
        this.skills = skills;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }

    public Integer getGraduationYear() { return graduationYear; }
    public void setGraduationYear(Integer graduationYear) { this.graduationYear = graduationYear; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }

    public String getGithub() { return github; }
    public void setGithub(String github) { this.github = github; }

    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
}
