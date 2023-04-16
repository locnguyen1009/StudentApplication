package com.example.student.repositories;

import com.example.student.entity.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepo {
    List<Enrollment> getEnrollments();
    Optional<Enrollment> getEnrollmentById(String id);
    Enrollment createEnrollment(Enrollment enrollment);
    Optional<Enrollment> updateEnrollment(String EnrollmentId, Enrollment enrollment);
}
