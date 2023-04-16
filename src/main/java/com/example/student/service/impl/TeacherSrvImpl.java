package com.example.student.service.impl;

import com.example.student.entity.Teacher;
import com.example.student.repositories.TeacherRepo;
import com.example.student.request.TeacherReq;
import com.example.student.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeacherSrvImpl implements TeacherService {

    private final TeacherRepo teacherRepo;

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepo.getAllTeacher();
    }
    @Override
    public Optional<Teacher> getTeacherById(String teacherId) {
        return teacherRepo.getTeacherById(teacherId);
    }

    @Override
    public Teacher addTeacher(TeacherReq teacherReq){
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherReq.getFirstName());
        teacher.setLastName(teacherReq.getLastName());
        teacher.setSpecializedCourses(teacherReq.getSpecializedCourses());
        return teacherRepo.addTeacher(teacher);
    }

    @Override
    public Optional<Teacher> updateTeacher(String teacherId, Teacher teacher) {
        return teacherRepo.updateTeacher(teacherId, teacher);
    }

    @Override
    public void deleteTeacher(String teacherId) {
        teacherRepo.deleteTeacher(teacherId);

    }
}
