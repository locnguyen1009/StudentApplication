package com.example.student.service.impl;

import com.example.student.domain.Teacher;
import com.example.student.service.TeacherService;
import com.example.student.service.repositories.impl.TeacherRepoImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherSrvImpl implements TeacherService {

    private final TeacherRepoImpl teacherRepo;

    public TeacherSrvImpl(TeacherRepoImpl teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepo.getAllTeacher();

    }
    @Override
    public Optional<Teacher> getTeacherById(Long teacherId) {
        return teacherRepo.getTeacherById(teacherId);
    }

    @Override
    public Teacher addTeacher(Teacher teacher){
        return teacherRepo.addTeacher(teacher);
    }

    @Override
    public Optional<Teacher> updateTeacher(Long teacherId, Teacher teacher) {
        return teacherRepo.updateTeacher(teacherId, teacher);
    }

    @Override
    public void deleteTeacher(Long teacherId) {

    }


}
