package com.example.student.service;

import com.example.student.domain.Student;

public interface StudentService {

//    TODO: what methods do CRUD operations need?

    Student getStudentById(long id);
    Student addStudent (Student student);
    String updateStudent (Long id, Student student);
    String deleteStudent (Long id);

}
