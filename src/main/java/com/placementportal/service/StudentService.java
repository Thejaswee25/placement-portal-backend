package com.placementportal.service;

import com.placementportal.dto.ApplicationDTO;
import com.placementportal.dto.StudentProfileDTO;
import com.placementportal.entity.*;
import com.placementportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    // ---- Get student profile by email ----

    public StudentProfileDTO getProfile(String email) {
        User user = getUserByEmail(email);
        StudentProfile profile = studentProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found. Please create your profile."));
        return mapToProfileDTO(profile);
    }

    // ---- Create or update student profile ----

    @Transactional
    public StudentProfileDTO saveProfile(String email, StudentProfileDTO dto) {
        User user = getUserByEmail(email);

        StudentProfile profile = studentProfileRepository.findByUser(user)
                .orElse(new StudentProfile());

        profile.setUser(user);
        profile.setBranch(dto.getBranch());
        profile.setCgpa(dto.getCgpa());
        profile.setGraduationYear(dto.getGraduationYear());
        profile.setPhone(dto.getPhone());
        profile.setLinkedin(dto.getLinkedin());
        profile.setGithub(dto.getGithub());

        StudentProfile savedProfile = studentProfileRepository.save(profile);

        // Handle skills update
        if (dto.getSkills() != null) {
            skillRepository.deleteByStudent(savedProfile);
            List<Skill> skills = dto.getSkills().stream()
                    .map(s -> new Skill(savedProfile, s))
                    .collect(Collectors.toList());
            skillRepository.saveAll(skills);
        }

        return mapToProfileDTO(savedProfile);
    }

    // ---- Update resume URL after upload ----

    public void updateResumeUrl(String email, String resumeUrl) {
        User user = getUserByEmail(email);
        StudentProfile profile = studentProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found. Create your profile first."));
        profile.setResumeUrl(resumeUrl);
        studentProfileRepository.save(profile);
    }

    // ---- Apply to a company (job drive) ----

    @Transactional
    public ApplicationDTO applyToCompany(String email, Long companyId) {
        User user = getUserByEmail(email);

        StudentProfile profile = studentProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Please create your profile before applying."));

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));

        if (applicationRepository.existsByStudentIdAndCompanyId(profile.getId(), companyId)) {
            throw new RuntimeException("You have already applied to this company.");
        }

        Application application = new Application();
        application.setStudent(profile);
        application.setCompany(company);
        application.setStatus(Application.Status.APPLIED);
        application.setAppliedDate(LocalDate.now());

        Application saved = applicationRepository.save(application);
        return mapToApplicationDTO(saved);
    }

    // ---- Get all applications for a student ----

    public List<ApplicationDTO> getMyApplications(String email) {
        User user = getUserByEmail(email);
        StudentProfile profile = studentProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found."));

        return applicationRepository.findByStudent(profile)
                .stream()
                .map(this::mapToApplicationDTO)
                .collect(Collectors.toList());
    }

    // ---- Get all interview schedules for a student ----

    public List<Interview> getMyInterviews(String email) {
        User user = getUserByEmail(email);
        StudentProfile profile = studentProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found."));
        return interviewRepository.findByStudentId(profile.getId());
    }

    // ---- Helper: get user by email ----

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
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
