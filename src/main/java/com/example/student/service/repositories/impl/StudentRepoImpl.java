package com.example.student.service.repositories.impl;

import com.example.student.domain.Student;
import com.example.student.service.repositories.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: implement StudentRepo interface here
@Service
public class StudentRepoImpl implements StudentRepo {

    private Map<Long, Student> students = new HashMap<>();
    private Long count = 0L;

    public List<Student> getStudents(){
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudentById(long id) {
        return students.get(id);
    }

    @Override
    public Student addStudent(Student student) {
        count++;
        student.setId(count);
        students.put(count, student);
        return student;
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        if(!students.containsKey(id)){
            return null;
        }
        student.setId(id);
        students.put(id, student);
        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        students.remove(id);
    }
}
