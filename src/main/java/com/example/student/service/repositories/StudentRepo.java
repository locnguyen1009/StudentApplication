package com.example.student.service.repositories;

import com.example.student.domain.Student;


// TODO: define the method that use for CRUD operation here
public interface StudentRepo {
    Student getStudentById(long id);
    Student addStudent (Student student);
    Student updateStudent (Long id, Student student);
    void deleteStudent (Long id);
}
