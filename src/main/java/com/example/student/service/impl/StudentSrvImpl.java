package com.example.student.service.impl;

import com.example.student.domain.Student;
import com.example.student.service.StudentService;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentSrvImpl implements StudentService {

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
//            TODO: question: is it necessary to set studentId here?
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
