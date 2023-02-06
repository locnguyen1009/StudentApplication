package com.example.student.repositories.impl;

import com.example.student.domain.Teacher;
import com.example.student.repositories.TeacherRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherRepoImpl implements TeacherRepo {

    private List<Teacher> teachers = new ArrayList<>();
    private long count = 0L;

    public List<Teacher> getAllTeacher() {
        return teachers;
    }

    @Override
    public Optional<Teacher> getTeacherById(Long teacherId) {
        return teachers.stream()
                .filter(x-> x.getId().equals(teacherId))
                .findFirst();
    }
    @Override
    public Teacher addTeacher(Teacher teacher){
        count++;
        teacher.setId(count);
        teachers.add(teacher);
        return teacher;
    }

    @Override
    public Optional<Teacher> updateTeacher(Long teacherId, Teacher teacher) {
        return Optional.empty();
    }

    @Override
    public void deleteTeacher(Long teacherId) {

    }

}
