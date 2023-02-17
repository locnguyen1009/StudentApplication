package com.example.student.service.impl;

import com.example.student.domain.Teacher;
import com.example.student.repositories.TeacherRepo;
import com.example.student.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherSrvImpl implements TeacherService {

    private final TeacherRepo teacherRepo;

    public TeacherSrvImpl(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepo.getAllTeacher();
    }
    @Override
    public Optional<Teacher> getTeacherById(String teacherId) {
        return teacherRepo.getTeacherById(teacherId);
    }

    @Override
    public Teacher addTeacher(Teacher teacher){
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
