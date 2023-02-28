package com.example.student.repositories;

import com.example.student.domain.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepo {
    List<Enrollment> getEnrollment();
    Optional<Enrollment> getEnrollmentById(String id);
    Enrollment addEnrollment(Enrollment enrollment);
    Optional<Enrollment> updateEnrollment(String id, Enrollment enrollment);
    void deleteEnrollment(String id);
}
