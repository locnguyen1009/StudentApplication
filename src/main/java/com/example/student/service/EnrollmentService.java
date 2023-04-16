package com.example.student.service;

import com.example.student.entity.Enrollment;
import com.example.student.response.EnrollmentResp;
import com.example.student.response.EnrollmentResult;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollment();
    List<EnrollmentResp> getAllEnrollmentsDetail();
    Optional<Enrollment> getEnrollmentById(String id);
    Optional<EnrollmentResp> getEnrollmentDetailById(String id);
    Optional<Enrollment> createEnrollment(Enrollment enrollment, String courseId);
    Optional<Enrollment> updateEnrollment(String id, Enrollment enrollment);

    Optional<EnrollmentResult> assignTeacherToEnrollment(String enrollmentId, String teacherId);

    Optional<EnrollmentResult> enrollStudents(String enrollmentId, String studentId);
    Optional<EnrollmentResp> removeStudents(String enrollmentId, String studentId);
}
