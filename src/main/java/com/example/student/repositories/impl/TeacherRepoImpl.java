package com.example.student.repositories.impl;

import com.example.student.domain.Student;
import com.example.student.domain.Teacher;
import com.example.student.repositories.TeacherRepo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherRepoImpl implements TeacherRepo {

    private final String collectionName = "Teacher";

    private final MongoTemplate mongo;

    public TeacherRepoImpl(MongoTemplate mongoTemplate) {
        this.mongo = mongoTemplate;
    }

    public List<Teacher> getAllTeacher() {
        return this.mongo.findAll(Teacher.class, collectionName);
    }

    @Override
    public Optional<Teacher> getTeacherById(String teacherId) {
        return Optional.ofNullable(this.mongo.findById(teacherId, Teacher.class,collectionName));
    }
    @Override
    public Teacher addTeacher(Teacher teacher){
        return this.mongo.save(teacher, collectionName);
    }

    @Override
    public Optional<Teacher> updateTeacher(String id, Teacher teacher) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        if(!this.mongo.exists(query, Teacher.class, collectionName)){
            return Optional.empty();
        }
        teacher.setId(id);
        return Optional.of(this.mongo.save(teacher, collectionName));

    }

    @Override
    public void deleteTeacher(String teacherId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("teacherId").is(teacherId));
        this.mongo.remove(query, Teacher.class, collectionName);
    }

}
