package com.example.student.service.impl;

import com.example.student.domain.Course;
import com.example.student.repositories.CourseRepo;
import com.example.student.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseSrvImpl implements CourseService {

    private final CourseRepo courseRepo;

    public CourseSrvImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepo.getAllCourse();
    }

    @Override
    public Optional<Course> getCourseById(String courseId) {
        return courseRepo.getCourseById(courseId);
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepo.addCourse(course);
    }

    @Override
    public Optional<Course> updateCourse(String courseId, Course course) {
        return courseRepo.updateCourse(courseId, course);
    }

    @Override
    public void deleteCourse(String courseId) {
        courseRepo.deleteCourse(courseId);
    }
}
