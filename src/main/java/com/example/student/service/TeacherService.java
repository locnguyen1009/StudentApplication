package com.example.student.service;

import com.example.student.domain.Student;
import com.example.student.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeacherService {

    List<Teacher> getAllTeacher();

    Optional<Teacher> getTeacherById(String teacherId);

    Teacher addTeacher(Teacher teacher);

    Optional<Teacher> updateTeacher(String teacherId, Teacher teacher);

    void deleteTeacher(String teacherId);
}
