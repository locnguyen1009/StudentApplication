package com.example.student.service;

import com.example.student.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {


//   TODO: read java.util.Optional in OCP book. What would be better in case the student doesn't exist, null or Optional of empty?
    Optional<Student> getStudentById(long id);
    Student addStudent (Student student);
    Optional<Student> updateStudent (Long id, Student student);
    void deleteStudent (Long id);


    List<Student> getStudents();
}
