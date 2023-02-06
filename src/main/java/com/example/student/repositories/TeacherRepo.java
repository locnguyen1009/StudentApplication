package com.example.student.repositories;

import com.example.student.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepo {
    Optional<Teacher> getTeacherById(Long teacherId);
    Teacher addTeacher(Teacher teacher);
    Optional<Teacher> updateTeacher(Long teacherId, Teacher teacher);
    void deleteTeacher (Long teacherId);
}
