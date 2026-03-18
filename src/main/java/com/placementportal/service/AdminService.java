package com.placementportal.service;

import com.placementportal.dto.ApplicationDTO;
import com.placementportal.dto.StudentProfileDTO;
import com.placementportal.entity.*;
import com.placementportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    // ---- Get all students with profiles ----

    public List<StudentProfileDTO> getAllStudents() {
        return studentProfileRepository.findAll()
                .stream()
                .map(this::mapToProfileDTO)
                .collect(Collectors.toList());
    }

    // ---- Get one student profile by profile id ----

    public StudentProfileDTO getStudentById(Long profileId) {
        StudentProfile profile = studentProfileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Student profile not found with id: " + profileId));
        return mapToProfileDTO(profile);
    }

    // ---- Get all applications ----

    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToApplicationDTO)
                .collect(Collectors.toList());
    }

    // ---- Shortlist a student application ----

    public ApplicationDTO shortlistApplication(Long applicationId) {
        return updateApplicationStatus(applicationId, Application.Status.SHORTLISTED);
    }

    // ---- Reject a student application ----

    public ApplicationDTO rejectApplication(Long applicationId) {
        return updateApplicationStatus(applicationId, Application.Status.REJECTED);
    }

    // ---- Select a student application ----

    public ApplicationDTO selectApplication(Long applicationId) {
        return updateApplicationStatus(applicationId, Application.Status.SELECTED);
    }

    // ---- Internal: update status helper ----

    private ApplicationDTO updateApplicationStatus(Long applicationId, Application.Status status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + applicationId));
        application.setStatus(status);
        Application saved = applicationRepository.save(application);
        return mapToApplicationDTO(saved);
    }

    // ---- Helper: map profile to DTO ----

    private StudentProfileDTO mapToProfileDTO(StudentProfile profile) {
        List<String> skills = skillRepository.findByStudent(profile)
                .stream()
                .map(Skill::getSkillName)
                .collect(Collectors.toList());

        StudentProfileDTO dto = new StudentProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setName(profile.getUser().getName());
        dto.setEmail(profile.getUser().getEmail());
        dto.setBranch(profile.getBranch());
        dto.setCgpa(profile.getCgpa());
        dto.setGraduationYear(profile.getGraduationYear());
        dto.setPhone(profile.getPhone());
        dto.setLinkedin(profile.getLinkedin());
        dto.setGithub(profile.getGithub());
        dto.setResumeUrl(profile.getResumeUrl());
        dto.setSkills(skills);
        return dto;
    }

    // ---- Helper: map application to DTO ----

    private ApplicationDTO mapToApplicationDTO(Application app) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(app.getId());
        dto.setStudentId(app.getStudent().getId());
        dto.setStudentName(app.getStudent().getUser().getName());
        dto.setStudentEmail(app.getStudent().getUser().getEmail());
        dto.setBranch(app.getStudent().getBranch());
        dto.setCgpa(app.getStudent().getCgpa());
        dto.setCompanyId(app.getCompany().getId());
        dto.setCompanyName(app.getCompany().getCompanyName());
        dto.setRole(app.getCompany().getRole());
        dto.setStatus(app.getStatus().name());
        dto.setAppliedDate(app.getAppliedDate());
        return dto;
    }
}
