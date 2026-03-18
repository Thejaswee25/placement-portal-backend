package com.placementportal.service;

import com.placementportal.dto.InterviewDTO;
import com.placementportal.entity.Application;
import com.placementportal.entity.Interview;
import com.placementportal.repository.ApplicationRepository;
import com.placementportal.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    // ---- Schedule an interview (Admin) ----

    public Interview scheduleInterview(InterviewDTO dto) {
        Application application = applicationRepository.findById(dto.getApplicationId())
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + dto.getApplicationId()));

        Interview interview = new Interview();
        interview.setApplication(application);
        interview.setInterviewDate(dto.getInterviewDate());
        interview.setInterviewTime(dto.getInterviewTime());
        interview.setMode(Interview.Mode.valueOf(dto.getMode().toUpperCase()));
        interview.setLocation(dto.getLocation());

        return interviewRepository.save(interview);
    }

    // ---- Get all interviews ----

    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }

    // ---- Get interview by id ----

    public Interview getInterviewById(Long id) {
        return interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found with id: " + id));
    }
}
