package com.example.student.service;

import com.example.student.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAllCourse();

    Optional<Course> getCourseById(String courseId);

    Course addCourse(Course course);

    Optional<Course> updateCourse(String courseId, Course course);

    void deleteCourse(String courseId);

}
