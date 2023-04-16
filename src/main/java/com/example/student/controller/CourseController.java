package com.example.student.controller;

import com.example.student.entity.Course;
import com.example.student.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById (@PathVariable String courseId){
        Optional<Course> course = courseService.getCourseById(courseId);
        return course
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not Found"));
    }

    @PostMapping("")
    public Course addCourse (@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse (@PathVariable String courseId, @RequestBody Course course) {
        Optional<Course> updatedCourse= courseService.updateCourse(courseId, course);
        return updatedCourse
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseId) {
        if(courseService.getCourseById(courseId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok().build();

    }
}
