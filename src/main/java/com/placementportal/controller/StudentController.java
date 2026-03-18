package com.placementportal.controller;

import com.placementportal.dto.*;
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

    // GET PROFILE
    @GetMapping("/profile")
    public ResponseEntity<StudentProfileDTO> getProfile(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                studentService.getProfile(userDetails.getUsername())
        );
    }

    // UPDATE PROFILE
    @PutMapping("/profile")
    public ResponseEntity<StudentProfileDTO> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody StudentProfileDTO dto) {
        return ResponseEntity.ok(
                studentService.saveProfile(userDetails.getUsername(), dto)
        );
    }

    // UPLOAD RESUME
    @PostMapping("/upload-resume")
    public ResponseEntity<Map<String, String>> uploadResume(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Please select a file"));
        }

        if (!"application/pdf".equals(file.getContentType())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Only PDF allowed"));
        }

        String fileName = fileUploadUtil.saveFile(file, userDetails.getUsername());

        String fileUrl = "https://placement-portal-backend-cjgw.onrender.com/uploads/resumes/" + fileName;

        studentService.updateResumeUrl(userDetails.getUsername(), fileUrl);

        return ResponseEntity.ok(Map.of(
                "message", "Upload success",
                "resumeUrl", fileUrl
        ));
    }

    // JOBS
    @GetMapping("/jobs")
    public ResponseEntity<List<CompanyDTO>> getJobs() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    // APPLY
    @PostMapping("/apply/{jobId}")
    public ResponseEntity<ApplicationDTO> applyToJob(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long jobId) {
        return ResponseEntity.ok(
                studentService.applyToCompany(userDetails.getUsername(), jobId)
        );
    }

    // APPLICATIONS
    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationDTO>> getMyApplications(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                studentService.getMyApplications(userDetails.getUsername())
        );
    }

    // INTERVIEWS
    @GetMapping("/interviews")
    public ResponseEntity<List<Interview>> getMyInterviews(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                studentService.getMyInterviews(userDetails.getUsername())
        );
    }
}
