package com.placementportal.repository;

import com.placementportal.entity.Application;
import com.placementportal.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudent(StudentProfile student);
    List<Application> findByStudentId(Long studentId);
    Optional<Application> findByStudentIdAndCompanyId(Long studentId, Long companyId);
    boolean existsByStudentIdAndCompanyId(Long studentId, Long companyId);
}
