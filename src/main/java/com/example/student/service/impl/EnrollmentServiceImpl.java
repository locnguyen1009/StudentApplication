package com.example.student.service.impl;

import com.example.student.domain.Course;
import com.example.student.domain.Enrollment;
import com.example.student.domain.Student;
import com.example.student.domain.Teacher;
import com.example.student.repositories.CourseRepo;
import com.example.student.repositories.EnrollmentRepo;
import com.example.student.repositories.StudentRepo;
import com.example.student.repositories.TeacherRepo;
import com.example.student.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {


    private final EnrollmentRepo enrollmentRepo;

    private final CourseRepo courseRepo;

    private final TeacherRepo teacherRepo;

    private final StudentRepo studentRepo;


    @Override
    public List<Enrollment> getEnrollment() {
        return enrollmentRepo.getEnrollment();
    }

    @Override
    public Optional<Enrollment> getEnrollmentById(String id) {
        return Optional.empty();
    }

    @Override
    public Enrollment addEnrollment(Enrollment enrollment) {
        return enrollmentRepo.addEnrollment(enrollment);
    }

    @Override
    public Optional<Enrollment> updateEnrollment(String id, Enrollment enrollment) {
        return enrollmentRepo.updateEnrollment(id, enrollment);
    }

    @Override
    public Optional<Enrollment> assignCourseToEnrollment(String enrollmentId, String courseId) {
        Enrollment enrollment = enrollmentRepo.getEnrollmentById(enrollmentId).get();
        Course course = courseRepo.getCourseById(courseId).get();
        enrollment.setCourseId(course);
        return enrollmentRepo.updateEnrollment(enrollmentId,enrollment);
    }

    @Override
    public Optional<Enrollment> assignTeacherToEnrollment(String enrollmentId, String teacherId) {
        Enrollment enrollment = enrollmentRepo.getEnrollmentById(enrollmentId).get();
        Teacher teacher = teacherRepo.getTeacherById(teacherId).get();
        enrollment.setTeacherId(teacher);
        return enrollmentRepo.updateEnrollment(enrollmentId,enrollment);
    }

    @Override
    public Optional<Enrollment> assignStudentsToEnrollment(String enrollmentId, String studentId) {
        Enrollment enrollment = enrollmentRepo.getEnrollmentById(enrollmentId).get();
        Student student = studentRepo.getStudentById(studentId).get();
        enrollment.getStudentIds().add(student);
        return enrollmentRepo.updateEnrollment(enrollmentId,enrollment);
    }

    @Override
    public void deleteEnrollment(String id) {
        enrollmentRepo.deleteEnrollment(id);
    }

}
