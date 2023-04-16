package com.example.student.repositories.impl;

import com.example.student.entity.Student;
import com.example.student.repositories.StudentRepo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentRepoImpl implements StudentRepo {

//    TODO: using MongoTemplate to store data into MongoDb

    private final String collectionName = "Student";

    private final MongoTemplate mongo;

    public StudentRepoImpl(MongoTemplate mongoTemplate) {
        this.mongo = mongoTemplate;
    }

    @Override
    public List<Student> getStudents(){
        return this.mongo.findAll(Student.class, collectionName);
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        return Optional.ofNullable(this.mongo.findById(id,Student.class,collectionName));

    }

    @Override
    public Student addStudent(Student student) {
        return this.mongo.insert(student, collectionName);
    }

    @Override
    public Optional<Student> updateStudent(String id, Student student) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        if(!this.mongo.exists(query, Student.class, collectionName)){
            return Optional.empty();
        }
        student.setId(id);
        return Optional.of(this.mongo.save(student, collectionName));
    }

    @Override
    public void deleteStudent(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        this.mongo.remove(query, Student.class, collectionName);
    }
}
