package com.example.student.repositories.impl;

import com.example.student.domain.Student;
import com.example.student.repositories.StudentRepo;
import net.minidev.json.writer.UpdaterMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

@Service
public class StudentRepoImpl implements StudentRepo {

//    TODO: using MongoTemplate to store data into MongoDb
//    remove in memory map
    private final Map<Long, Student> students = new HashMap<>();
    private final String collectionName = "Student";
    private Long count = 0L;

    private MongoTemplate mongo;

    public StudentRepoImpl(MongoTemplate mongoTemplate) {
        this.mongo = mongoTemplate;
    }

    public List<Student> getStudents(){
        return this.mongo.findAll(Student.class, collectionName);
    }

    @Override
    public Optional<Student> getStudentById(long id) {
        return Optional.ofNullable(this.mongo.findById(id,Student.class,collectionName));

    }

    @Override
    public Student addStudent(Student student) {
        count++;
        student.setId(count);
        return this.mongo.insert(student, collectionName);
    }

    @Override
    public Optional<Student> updateStudent(Long id, Student student) {
//        if(!students.containsKey(id)){
//            return Optional.empty();
//        }
//        student.setId(id);
//        students.put(id, student);
//        return Optional.of(student);

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        if(!this.mongo.exists(query, Student.class, collectionName)){
            return Optional.empty();
        }
        student.setId(id);
        return Optional.of(this.mongo.insert(student, collectionName));

    }

    @Override
    public void deleteStudent(Long id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        this.mongo.findAndRemove(query, Student.class, collectionName);
    }
}
