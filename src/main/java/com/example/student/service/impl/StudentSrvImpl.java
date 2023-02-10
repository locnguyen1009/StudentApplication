package com.example.student.service.impl;

import com.example.student.domain.Student;
import com.example.student.service.StudentService;
import com.example.student.repositories.impl.StudentRepoImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentSrvImpl implements StudentService {

//    TODO: inject StudentRepo here to interact with persistent layer
//    TODO: remove students map from here.

    private final StudentRepoImpl studentRepoImpl;

    public StudentSrvImpl(StudentRepoImpl studentRepo) {
        this.studentRepoImpl = studentRepo;
    }

    public List<Student> getStudents(){
        return studentRepoImpl.getStudents();
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        return studentRepoImpl.getStudentById(id);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepoImpl.addStudent(student);
    }

    @Override
    public Optional<Student> updateStudent(String id, Student student) {
            return studentRepoImpl.updateStudent(id, student);
    }

    @Override
    public void deleteStudent(String id) {
        studentRepoImpl.deleteStudent(id);
    }
}
