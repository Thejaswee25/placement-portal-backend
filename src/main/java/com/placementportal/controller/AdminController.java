package com.placementportal.controller;

import com.placementportal.dto.ApplicationDTO;
import com.placementportal.dto.CompanyDTO;
import com.placementportal.dto.InterviewDTO;
import com.placementportal.dto.StudentProfileDTO;
import com.placementportal.entity.Interview;
import com.placementportal.service.AdminService;
import com.placementportal.service.CompanyService;
import com.placementportal.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private InterviewService interviewService;

    // GET /api/admin/students
    @GetMapping("/students")
    public ResponseEntity<List<StudentProfileDTO>> getAllStudents() {
        return ResponseEntity.ok(adminService.getAllStudents());
    }

    // GET /api/admin/student/{id}
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentProfileDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getStudentById(id));
    }

    // GET /api/admin/student/{id}/resume  — download resume
    @GetMapping("/student/{id}/resume")
    public ResponseEntity<Resource> downloadResume(@PathVariable Long id) throws MalformedURLException {
        StudentProfileDTO student = adminService.getStudentById(id);

        if (student.getResumeUrl() == null || student.getResumeUrl().isBlank()) {
            return ResponseEntity.notFound().build();
        }

        // resumeUrl stored as "/uploads/resumes/filename.pdf"
        String relativePath = student.getResumeUrl().replaceFirst("^/", "");
        Path filePath = Paths.get(relativePath).toAbsolutePath().normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // GET /api/admin/applications
    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        return ResponseEntity.ok(adminService.getAllApplications());
    }

    // PUT /api/admin/application/{id}/shortlist
    @PutMapping("/application/{id}/shortlist")
    public ResponseEntity<ApplicationDTO> shortlistApplication(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.shortlistApplication(id));
    }

    // PUT /api/admin/application/{id}/reject
    @PutMapping("/application/{id}/reject")
    public ResponseEntity<ApplicationDTO> rejectApplication(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.rejectApplication(id));
    }

    // PUT /api/admin/application/{id}/select
    @PutMapping("/application/{id}/select")
    public ResponseEntity<ApplicationDTO> selectApplication(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.selectApplication(id));
    }

    // POST /api/admin/company
    @PostMapping("/company")
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO dto) {
        return ResponseEntity.ok(companyService.addCompany(dto));
    }

    // PUT /api/admin/company/{id}
    @PutMapping("/company/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO dto) {
        return ResponseEntity.ok(companyService.updateCompany(id, dto));
    }

    // DELETE /api/admin/company/{id}
    @DeleteMapping("/company/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/admin/interview
    @PostMapping("/interview")
    public ResponseEntity<Interview> scheduleInterview(@RequestBody InterviewDTO dto) {
        return ResponseEntity.ok(interviewService.scheduleInterview(dto));
    }

    // GET /api/admin/interviews
    @GetMapping("/interviews")
    public ResponseEntity<List<Interview>> getAllInterviews() {
        return ResponseEntity.ok(interviewService.getAllInterviews());
    }
}
