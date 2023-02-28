package com.example.student.service;

import com.example.student.domain.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {
    List<Enrollment> getEnrollment();
    Optional<Enrollment> getEnrollmentById(String id);
    Enrollment addEnrollment(Enrollment enrollment);
    Optional<Enrollment> updateEnrollment(String id, Enrollment enrollment);
    void deleteEnrollment(String id);
    Optional<Enrollment> assignTeacherToEnrollment(String enrollmentId, String teacherId);

    Optional<Enrollment> assignCourseToEnrollment(String enrollmentId, String courseId);

    Optional<Enrollment> assignStudentsToEnrollment(String enrollmentId, String studentId);
}
