package com.example.student.service.repositories.impl;

import com.example.student.domain.Student;
import com.example.student.service.repositories.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.*;

//TODO: implement StudentRepo interface here
@Service
public class StudentRepoImpl implements StudentRepo {

    private final Map<Long, Student> students = new HashMap<>();
    private Long count = 0L;

    public List<Student> getStudents(){
        return new ArrayList<>(students.values());
    }

    @Override
    public Optional<Student> getStudentById(long id) {
        if(!students.containsKey(id)){
            return Optional.empty();
        }
        return Optional.of(students.get(id));

    }

    @Override
    public Student addStudent(Student student) {
        count++;
        student.setId(count);
        students.put(count, student);
        return student;
    }

    @Override
    public Optional<Student> updateStudent(Long id, Student student) {
        if(!students.containsKey(id)){
            return Optional.empty();
        }
        student.setId(id);
        students.put(id, student);
        return Optional.of(student);
    }

    @Override
    public void deleteStudent(Long id) {
        students.remove(id);
    }
}
