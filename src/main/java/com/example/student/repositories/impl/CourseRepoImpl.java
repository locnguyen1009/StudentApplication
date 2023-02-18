package com.example.student.repositories.impl;

import com.example.student.domain.Course;
import com.example.student.repositories.CourseRepo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRepoImpl implements CourseRepo {
    private final String collectionName = "Course";
    private MongoTemplate mongo;

    public CourseRepoImpl(MongoTemplate mongoTemplate) {
        this.mongo = mongoTemplate;
    }

    @Override
    public List<Course> getAllCourse() {
        return this.mongo.findAll(Course.class, collectionName);
    }

    @Override
    public Optional<Course> getCourseById(String courseId) {
        return Optional.ofNullable(this.mongo.findById(courseId, Course.class,collectionName));
    }

    @Override
    public Course addCourse(Course course) {
        return this.mongo.save(course, collectionName);
    }

    @Override
    public Optional<Course> updateCourse(String courseId, Course course) {
        Query query = new Query();
        query.addCriteria(Criteria.where("CourseId").is(courseId));
        if(!this.mongo.exists(query, Course.class, collectionName)){
            return Optional.empty();
        }
        course.setId(courseId);
        return Optional.of(this.mongo.save(course, collectionName));

    }

    @Override
    public void deleteCourse(String courseId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(courseId));
        this.mongo.remove(query, Course.class, collectionName);
    }
}
