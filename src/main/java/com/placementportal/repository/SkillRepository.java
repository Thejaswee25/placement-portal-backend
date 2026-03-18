package com.placementportal.repository;

import com.placementportal.entity.Skill;
import com.placementportal.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByStudent(StudentProfile student);
    List<Skill> findByStudentId(Long studentId);
    void deleteByStudent(StudentProfile student);
}
