package com.placementportal.service;

import com.placementportal.dto.ApplicationDTO;
import com.placementportal.entity.Application;
import com.placementportal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ApplicationDTO getApplicationById(Long id) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
        return mapToDTO(app);
    }

    private ApplicationDTO mapToDTO(Application app) {
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
