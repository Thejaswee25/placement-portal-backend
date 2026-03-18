package com.placementportal.service;

import com.placementportal.dto.CompanyDTO;
import com.placementportal.entity.Company;
import com.placementportal.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // ---- Get all companies ----

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ---- Get company by id ----

    public CompanyDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        return mapToDTO(company);
    }

    // ---- Add a new company drive (Admin only) ----

    public CompanyDTO addCompany(CompanyDTO dto) {
        Company company = new Company();
        company.setCompanyName(dto.getCompanyName());
        company.setRole(dto.getRole());
        company.setPackageOffered(dto.getPackageOffered());
        company.setLocation(dto.getLocation());
        company.setCgpaCriteria(dto.getCgpaCriteria());
        company.setEligibleBranches(dto.getEligibleBranches());
        company.setApplicationDeadline(dto.getApplicationDeadline());

        Company saved = companyRepository.save(company);
        return mapToDTO(saved);
    }

    // ---- Update company ----

    public CompanyDTO updateCompany(Long id, CompanyDTO dto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));

        company.setCompanyName(dto.getCompanyName());
        company.setRole(dto.getRole());
        company.setPackageOffered(dto.getPackageOffered());
        company.setLocation(dto.getLocation());
        company.setCgpaCriteria(dto.getCgpaCriteria());
        company.setEligibleBranches(dto.getEligibleBranches());
        company.setApplicationDeadline(dto.getApplicationDeadline());

        return mapToDTO(companyRepository.save(company));
    }

    // ---- Delete company ----

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    // ---- Helper: map entity to DTO ----

    private CompanyDTO mapToDTO(Company company) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setCompanyName(company.getCompanyName());
        dto.setRole(company.getRole());
        dto.setPackageOffered(company.getPackageOffered());
        dto.setLocation(company.getLocation());
        dto.setCgpaCriteria(company.getCgpaCriteria());
        dto.setEligibleBranches(company.getEligibleBranches());
        dto.setApplicationDeadline(company.getApplicationDeadline());
        return dto;
    }
}
