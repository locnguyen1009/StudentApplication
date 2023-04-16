package com.example.student.service.impl;

import com.example.student.entity.Student;
import com.example.student.repositories.StudentRepo;
import com.example.student.request.StudentReq;
import com.example.student.service.StudentService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

//    TODO: inject StudentRepo here to interact with persistent layer
//    TODO: remove students map from here.

    private final StudentRepo studentRepo;

    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> getStudents(){
        return studentRepo.getStudents();
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        return studentRepo.getStudentById(id);
    }

    @Override
    public Student addStudent(StudentReq studentReq) {
        Student student = new Student();
        student.setGrade(studentReq.getGrade());
        student.setName(studentReq.getName());
        return studentRepo.addStudent(student);
    }

    @Override
    public Optional<Student> updateStudent(String id, Student student) {
            return studentRepo.updateStudent(id, student);
    }

    @Override
    public void deleteStudent(String id) {
        studentRepo.deleteStudent(id);
    }
}
