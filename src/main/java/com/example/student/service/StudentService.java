package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.request.StudentReq;


import java.util.List;
import java.util.Optional;

public interface StudentService {


//   TODO: read java.util.Optional in OCP book. What would be better in case the student doesn't exist, null or Optional of empty?
    Optional<Student> getStudentById(String id);
//    Student addStudent (Student student);

    Student addStudent(StudentReq studentReq);
    Optional<Student> updateStudent (String id, Student student);
    void deleteStudent (String id);
    List<Student> getStudents();
}
