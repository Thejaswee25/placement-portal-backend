package com.placementportal.controller;

import com.placementportal.dto.InterviewDTO;
import com.placementportal.entity.Interview;
import com.placementportal.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    // POST /api/interviews
    @PostMapping
    public ResponseEntity<Interview> scheduleInterview(@RequestBody InterviewDTO dto) {
        return ResponseEntity.ok(interviewService.scheduleInterview(dto));
    }

    // GET /api/interviews
    @GetMapping
    public ResponseEntity<List<Interview>> getAllInterviews() {
        return ResponseEntity.ok(interviewService.getAllInterviews());
    }

    // GET /api/interviews/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Interview> getInterviewById(@PathVariable Long id) {
        return ResponseEntity.ok(interviewService.getInterviewById(id));
    }
}
