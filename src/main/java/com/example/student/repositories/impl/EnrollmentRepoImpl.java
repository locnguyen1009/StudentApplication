package com.example.student.repositories.impl;

import com.example.student.entity.Enrollment;
import com.example.student.entity.Student;
import com.example.student.entity.Teacher;
import com.example.student.repositories.EnrollmentRepo;
import com.example.student.repositories.TeacherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentRepoImpl implements EnrollmentRepo {

    private final String collectionName = "Enrollment";

    private final MongoTemplate mongo;

    @Override
    public List<Enrollment> getEnrollments() {
        return this.mongo.findAll(Enrollment.class,collectionName);
    }

    @Override
    public Optional<Enrollment> getEnrollmentById(String id) {
        return Optional.ofNullable(this.mongo.findById(id,Enrollment.class,collectionName));
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        return this.mongo.insert(enrollment, collectionName);
    }

    @Override
    public Optional<Enrollment> updateEnrollment(String enrollmentId, Enrollment enrollment) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(enrollmentId));
//        if(!this.mongo.exists(query, Enrollment.class, collectionName)){
//            return Optional.empty();
//        }
        return Optional.of(this.mongo.save(enrollment, collectionName));
    }

}
