package com.example.student.service;

import com.example.student.domain.Student;

public interface StudentService {


//   TODO: read java.util.Optional in OCP book. What would be better in case the student doesn't exist, null or Optional of empty?
    Student getStudentById(long id);
    Student addStudent (Student student);
    Student updateStudent (Long id, Student student);
    void deleteStudent (Long id);

}
