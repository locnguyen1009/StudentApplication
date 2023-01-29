package com.example.student.service;

import com.example.student.domain.Student;

public interface StudentService {

//    TODO: what methods do CRUD operations need?

    Student getStudentById(long id);
    Student addStudent (Student student);
//    TODO: this method should return student with updated information instead of String
    Student updateStudent (Long id, Student student);
//   TODO: This method should return void
    void deleteStudent (Long id);

}
