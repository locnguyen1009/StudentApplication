package com.example.student.service.repositories;

import com.example.student.domain.Student;

import java.util.Optional;


// TODO: define the method that use for CRUD operation here
public interface StudentRepo {
    Optional<Student> getStudentById(long id);
    Student addStudent (Student student);
    Optional<Student> updateStudent (Long id, Student student);
    void deleteStudent (Long id);
}
