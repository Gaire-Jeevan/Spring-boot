package com.ltp.gradesubmission.repository;

import com.ltp.gradesubmission.entity.Grade;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    Optional<Grade> findByStudentIdAndCourseId(Long StudentId, Long courseId);
    
    @Transactional
    void deleteByStudentIdAndCourseId(Long StudentId, Long courseId);

    List<Grade> findByStudentId(Long studentId);

    List<Grade> findByCourseId(Long courseId);

}