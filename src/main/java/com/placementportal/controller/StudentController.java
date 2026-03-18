package com.placementportal.controller;

import com.placementportal.dto.ApplicationDTO;
import com.placementportal.dto.CompanyDTO;
import com.placementportal.dto.StudentProfileDTO;
import com.placementportal.entity.Interview;
import com.placementportal.service.CompanyService;
import com.placementportal.service.StudentService;
import com.placementportal.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    // GET /api/student/profile
    @GetMapping("/profile")
    public ResponseEntity<StudentProfileDTO> getProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        StudentProfileDTO profile = studentService.getProfile(userDetails.getUsername());
        return ResponseEntity.ok(profile);
    }

    // PUT /api/student/profile
    @PutMapping("/profile")
    public ResponseEntity<StudentProfileDTO> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody StudentProfileDTO dto) {
        StudentProfileDTO updated = studentService.saveProfile(userDetails.getUsername(), dto);
        return ResponseEntity.ok(updated);
    }

    // POST /api/student/upload-resume
    @PostMapping("/upload-resume")
    public ResponseEntity<Map<String, String>> uploadResume(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Please select a file to upload"));
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.equals("application/pdf")) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Only PDF files are allowed"));
        }

        String fileName = fileUploadUtil.saveFile(file, userDetails.getUsername());

        String fileUrl = "https://placement-portal-backend-cjgw.onrender.com/uploads/resumes/" + fileName;

        studentService.updateResumeUrl(userDetails.getUsername(), fileUrl);

        return ResponseEntity.ok(Map.of(
                "message", "Resume uploaded successfully",
                "resumeUrl", fileUrl
        ));
    }

    // GET /api/student/jobs
    @GetMapping("/jobs")
    public ResponseEntity<List<CompanyDTO>> getJobs() {
        List<CompanyDTO> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    // POST /api/student/apply/{jobId}
    @PostMapping("/apply/{jobId}")
    public ResponseEntity<ApplicationDTO> applyToJob(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long jobId) {
        ApplicationDTO application = studentService.applyToCompany(userDetails.getUsername(), jobId);
        return ResponseEntity.ok(application);
    }

    // GET /api/student/applications
    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationDTO>> getMyApplications(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<ApplicationDTO> applications = studentService.getMyApplications(userDetails.getUsername());
        return ResponseEntity.ok(applications);
    }

    // GET /api/student/interviews
    @GetMapping("/interviews")
    public ResponseEntity<List<Interview>> getMyInterviews(
            @AuthenticationPrincipal UserDetails userDetails) {
        List<Interview> interviews = studentService.getMyInterviews(userDetails.getUsername());
        return ResponseEntity.ok(interviews);
    }
}
