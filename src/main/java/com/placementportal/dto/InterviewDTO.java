package com.placementportal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class InterviewDTO {

    private Long applicationId;
    private LocalDate interviewDate;
    private LocalTime interviewTime;
    private String mode;
    private String location;

    // ---- Constructors ----

    public InterviewDTO() {}

    public InterviewDTO(Long applicationId, LocalDate interviewDate, LocalTime interviewTime,
                        String mode, String location) {
        this.applicationId = applicationId;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.mode = mode;
        this.location = location;
    }

    // ---- Getters & Setters ----

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }

    public LocalDate getInterviewDate() { return interviewDate; }
    public void setInterviewDate(LocalDate interviewDate) { this.interviewDate = interviewDate; }

    public LocalTime getInterviewTime() { return interviewTime; }
    public void setInterviewTime(LocalTime interviewTime) { this.interviewTime = interviewTime; }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
