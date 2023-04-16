package com.example.student.repositories;

import com.example.student.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepo {
    List<Teacher> getAllTeacher();
    Optional<Teacher> getTeacherById(String teacherId);
    Teacher addTeacher(Teacher teacher);
    Optional<Teacher> updateTeacher(String teacherId, Teacher teacher);
    void deleteTeacher (String teacherId);
}
