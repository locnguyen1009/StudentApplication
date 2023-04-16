package com.example.student.service.impl;

import com.example.student.entity.Course;
import com.example.student.entity.Enrollment;
import com.example.student.entity.Student;
import com.example.student.entity.Teacher;
import com.example.student.repositories.EnrollmentRepo;
import com.example.student.response.EnrollmentResp;
import com.example.student.response.EnrollmentResult;
import com.example.student.service.CourseService;
import com.example.student.service.EnrollmentService;
import com.example.student.service.StudentService;
import com.example.student.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {


    private final EnrollmentRepo enrollmentRepo;

    private final CourseService courseService;

    private final TeacherService teacherService;

    private final StudentService studentService;


    @Override
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepo.getEnrollments();
    }

    @Override
    public List<EnrollmentResp> getAllEnrollmentsDetail() {
        List<Enrollment> enrolls = enrollmentRepo.getEnrollments();
        List<EnrollmentResp> respList = new ArrayList<>();
        for(Enrollment i : enrolls){
            respList.add(getEnrollmentDetailById(i.getId()).get());
        }
        return respList;
//        return enrolls.parallelStream()
//                .map(enrollment -> getEnrollmentDetailById(enrollment.getId()).get())
//                .toList();
    }


    @Override
    public Optional<Enrollment> getEnrollmentById(String id) {
        return enrollmentRepo.getEnrollmentById(id);
    }


    @Override
    public Optional<EnrollmentResp> getEnrollmentDetailById(String enrollmentId) {
        Optional<Enrollment> enrollmentOp = enrollmentRepo.getEnrollmentById(enrollmentId);
        if(enrollmentOp.isPresent()){
            Enrollment enrollment = enrollmentOp.get();
            List<Student> students = enrollment.getStudentIds().stream()
                    .map(id -> studentService.getStudentById(id).get())
                    .toList();

            Course course = null;
            if(enrollment.getCourseId() != null){
                course = courseService.getCourseById(enrollment.getCourseId()).get();
            }

            Teacher teacher = null;
            if(enrollment.getTeacherId() != null){
                teacher = teacherService.getTeacherById(enrollment.getTeacherId()).get();
            }
            return Optional.of(
                    EnrollmentResp.builder()
                            .id(enrollment.getId())
                            .course(course)
                            .students(students)
                            .maxCapacity(enrollment.getMaxCapacity())
                            .teacher(teacher)
                            .begin(enrollment.getBegin())
                            .end(enrollment.getEnd())
                            .enrollDeadline(enrollment.getEnrollDeadline())
                            .build());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EnrollmentId is not found");
    }

    @Override
    public Optional<Enrollment> createEnrollment(Enrollment enrollment, String courseId) {
        if (courseService.getCourseById(courseId).isEmpty()) {
            return Optional.empty();
        }
        enrollment.setCourseId(courseId);
        return Optional.of(enrollmentRepo.createEnrollment(enrollment));
    }

    @Override
    public Optional<Enrollment> updateEnrollment(String id, Enrollment enrollment) {
        Optional<Enrollment> enrollmentOp = enrollmentRepo.getEnrollmentById(id);

        if(enrollmentOp.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EnrollmentId is not found");
        }

        Enrollment currentEnrollment = enrollmentOp.get();
        if(enrollment.getMaxCapacity() != null) {
            currentEnrollment.setMaxCapacity(enrollment.getMaxCapacity());
        }
        if(enrollment.getBegin()!=null){
            currentEnrollment.setBegin(enrollment.getBegin());
        }
        if(enrollment.getEnd()!=null){
            currentEnrollment.setEnd(enrollment.getEnd());
        }
        if(enrollment.getEnrollDeadline()!=null){
            currentEnrollment.setEnrollDeadline(enrollment.getEnrollDeadline());
        }
        return enrollmentRepo.updateEnrollment(id, currentEnrollment);
    }

    @Override
    public Optional<EnrollmentResult> assignTeacherToEnrollment(String enrollmentId, String teacherId) {
        Optional<Enrollment> enrollmentOp = enrollmentRepo.getEnrollmentById(enrollmentId);
        Optional<Teacher> teacherOp = teacherService.getTeacherById(teacherId);
//        return enrollmentOp.map(enrollment -> {
//           return teacherOp.map(teacher -> {
//               enrollment.setTeacherId(teacherId);
//               return enrollmentRepo.updateEnrollment(enrollmentId, enrollment);
//           }).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Teacher with Id " + teacherId + " does not exist"));
//        })
//        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enrollment with Id " + enrollmentId + " does not exist"));
        if (enrollmentOp.isPresent()) {
            var enrollment = enrollmentOp.get();
            String message = "";
            boolean status;
            if(teacherOp.isPresent()){
                enrollment.setTeacherId(teacherId);
                status = true;
                message = "Teacher added successfully";
                enrollmentRepo.updateEnrollment(enrollmentId, enrollment);
            } else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, " teacher does not exist");
            }

            return Optional.of(
                    EnrollmentResult.builder()
                            .enrollmentId(enrollment.getId())
                            .enrollStatus(status)
                            .message(message)
                            .build());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enrollment is not exist");
    }

    @Override
    public Optional<EnrollmentResult> enrollStudents(String enrollmentId, String studentId) {
        Optional<Enrollment> enrollmentOp = enrollmentRepo.getEnrollmentById(enrollmentId);
        Optional<Student> studentOp = studentService.getStudentById(studentId);
        if(enrollmentOp.isPresent() && studentOp.isPresent()) {
            Enrollment enrollment = enrollmentOp.get();
            Date deadline = enrollment.getEnrollDeadline();
            String message = "";
            boolean status = false;

            if (Date.from(Instant.now()).after(deadline)) {
                message = "Passed enrollment Deadline. Cannot registered";
            }
//            Date, Instant, Duration,

            if(enrollment.getStudentIds().size() < enrollment.getMaxCapacity()) {
                enrollment.getStudentIds().add(studentId);
                message = "student added";
                status = true;
            }else {
                message += " Maximum . Can't enrolled";
            }
            if (status) {
                enrollmentRepo.updateEnrollment(enrollmentId, enrollment);
            }
            return Optional.of(
                    EnrollmentResult.builder()
                            .enrollmentId(enrollment.getId())
                            .enrollStatus(status)
                            .message(message)
                            .build());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Either Student or Enrollment is not exist");
    }

    @Override
    public Optional<EnrollmentResp> removeStudents(String enrollmentId, String studentId) {
        Optional<Enrollment> enrollmentOp = enrollmentRepo.getEnrollmentById(enrollmentId);
        if (enrollmentOp.isPresent()) {
            Enrollment enrollment = enrollmentOp.get();
            boolean removeResult = enrollment.getStudentIds().remove(studentId);
            if (removeResult) {
                enrollmentRepo.updateEnrollment(enrollmentId, enrollment);
            }

            Course course = null;
            if (enrollment.getCourseId() != null) {
                course = courseService.getCourseById(enrollment.getCourseId()).get();
            }
            Teacher teacher = null;
            if(enrollment.getTeacherId() != null){
                teacher = teacherService.getTeacherById(enrollment.getTeacherId()).get();
            }
            List<Student> students = enrollment.getStudentIds().stream()
                    .map(id -> studentService.getStudentById(id).get())
                    .toList();
            return Optional.of(
                    EnrollmentResp.builder()
                            .id(enrollment.getId())
                            .course(course)
                            .students(students)
                            .maxCapacity(enrollment.getMaxCapacity())
                            .teacher(teacher)
                            .build());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Either Student or Enrollment is not exist");
    }
}
