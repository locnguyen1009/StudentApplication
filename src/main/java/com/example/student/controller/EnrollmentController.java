package com.example.student.controller;

import com.example.student.domain.Enrollment;
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
@RequestMapping("/enrollment")
public class EnrollmentController {
    // /regiserClass

    private final EnrollmentService enrollmentService;

    @GetMapping("")
    public List<Enrollment> getAllEnrollment() {
        return enrollmentService.getEnrollment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable String id){
        return enrollmentService.getEnrollmentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not Found"));
    }

    @PostMapping("")
    public Enrollment addEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.addEnrollment(enrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment (@PathVariable String id, Enrollment enrollment){
        return enrollmentService.updateEnrollment(id, enrollment)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not Found"));
    }

    @PutMapping("/{enrollmentId}/course/{courseId}")
    public ResponseEntity<Enrollment> assignCourseToEnrollment(@PathVariable String enrollmentId, @PathVariable String courseId){
        return enrollmentService.assignCourseToEnrollment(enrollmentId,courseId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not Found"));

    }
    @PutMapping("{enrollmentId}/teacher/{teacherId}")
    public ResponseEntity<Enrollment> assignTeacherToEnrollement(@PathVariable String enrollmentId, @PathVariable String teacherId){
        return enrollmentService.assignTeacherToEnrollment(enrollmentId, teacherId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not Found"));
    }

    @PutMapping("/{enrollmentId}/student/{studentId}")
    public ResponseEntity<Enrollment> assignStudentToEnrollement(@PathVariable String enrollmentId, @PathVariable String studentId){
        return enrollmentService.assignStudentsToEnrollment(enrollmentId, studentId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not Found"));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable String id) {
        if(enrollmentService.getEnrollmentById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok().build();
    }
}
