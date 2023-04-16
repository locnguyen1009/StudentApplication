package com.example.student.service;

import com.example.student.entity.Teacher;
import com.example.student.request.TeacherReq;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeacherService {

    List<Teacher> getAllTeacher();

    Optional<Teacher> getTeacherById(String teacherId);

    Teacher addTeacher(TeacherReq teacherReq);

    Optional<Teacher> updateTeacher(String teacherId, Teacher teacher);

    void deleteTeacher(String teacherId);
}
