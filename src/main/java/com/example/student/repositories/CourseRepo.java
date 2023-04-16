package com.example.student.repositories;

import com.example.student.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepo {
    List<Course> getAllCourse();

    Optional<Course> getCourseById(String courseId);

    Course addCourse(Course course);

    Optional<Course> updateCourse(String courseId, Course course);

    void deleteCourse(String courseId);
}
