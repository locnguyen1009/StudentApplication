package com.example.student.repositories.impl;

import com.example.student.domain.Enrollment;
import com.example.student.repositories.EnrollmentRepo;
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

    private final String collectionName = "CourseEnrollment";

    private final MongoTemplate mongo;

    @Override
    public List<Enrollment> getEnrollment() {
        return this.mongo.findAll(Enrollment.class,collectionName);
    }

    @Override
    public Optional<Enrollment> getEnrollmentById(String id) {
        return Optional.ofNullable(this.mongo.findById(id,Enrollment.class,collectionName));
    }

    @Override
    public Enrollment addEnrollment(Enrollment enrollment) {
        return this.mongo.insert(enrollment, collectionName);
    }

    @Override
    public Optional<Enrollment> updateEnrollment(String id, Enrollment enrollment) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        if(!this.mongo.exists(query, Enrollment.class, collectionName)){
            return Optional.empty();
        }
        enrollment.setId(id);
        return Optional.of(this.mongo.save(enrollment, collectionName));
    }

    @Override
    public void deleteEnrollment(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        this.mongo.remove(query, Enrollment.class, collectionName);
    }
}
