package com.example.student.service;

import com.example.student.domain.Student;
import com.example.student.domain.Teacher;

import java.util.Optional;

public interface TeacherService {

    Optional<Teacher> getTeacherById(Long teacherId);
    Teacher addTeacher(Teacher teacher);
    Optional<Teacher> updateTeacher(Long teacherId, Teacher teacher);
    void deleteTeacher (Long teacherId);
}
