package com.example.student.controller;

import com.example.student.entity.Enrollment;
import com.example.student.response.EnrollmentResp;
import com.example.student.response.EnrollmentResult;
import com.example.student.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController {
    // /regiserClass

    private final EnrollmentService enrollmentService;

    @GetMapping("")
    public List<Enrollment> getAllEnrollments(){
        return enrollmentService.getAllEnrollment();
    }

    @GetMapping("/detail")
    public List<EnrollmentResp> getAllEnrollmentsDetail() {
        return enrollmentService.getAllEnrollmentsDetail();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable String id){
        return enrollmentService.getEnrollmentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not Found"));
    }

    @GetMapping("/details/{enrollmentId}")
    public Optional<EnrollmentResp> getEnrollmentDetailById(@PathVariable String enrollmentId){
        return enrollmentService.getEnrollmentDetailById(enrollmentId);
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment, @PathVariable String courseId) {
        return enrollmentService.createEnrollment(enrollment, courseId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CourseId is not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment (@PathVariable String id, @RequestBody Enrollment enrollment){
        return enrollmentService.updateEnrollment(id, enrollment)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not Found"));
    }


    @PutMapping("{enrollmentId}/teacher/{teacherId}")
    public Optional<EnrollmentResult> assignTeacherToEnrollement(@PathVariable String enrollmentId, @PathVariable String teacherId){
        return enrollmentService.assignTeacherToEnrollment(enrollmentId, teacherId);
//                .map(ResponseEntity::ok)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not Found"));
    }

    @PutMapping("/{enrollmentId}/student/{studentId}")
    public Optional<EnrollmentResult> enrollStudents(@PathVariable String enrollmentId, @PathVariable String studentId){
        return enrollmentService.enrollStudents(enrollmentId, studentId);

    }

    @DeleteMapping("/{enrollmentId}/student/{studentId}")
    public Optional<EnrollmentResp> removeStudents(@PathVariable String enrollmentId, @PathVariable String studentId){
        return enrollmentService.removeStudents(enrollmentId, studentId);

    }

}
