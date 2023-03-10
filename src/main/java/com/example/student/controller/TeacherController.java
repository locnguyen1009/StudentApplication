package com.example.student.controller;


import com.example.student.domain.Student;
import com.example.student.domain.Teacher;
import com.example.student.repositories.TeacherRepo;
import com.example.student.service.TeacherService;
import com.example.student.service.impl.TeacherSrvImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherSrv;

    public TeacherController(TeacherService teacherSrv) {
        this.teacherSrv = teacherSrv;
    }

    @GetMapping("")
    public List<Teacher> getAllTeacher() {
        return teacherSrv.getAllTeacher();
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById (@PathVariable String teacherId){
        Optional<Teacher> teacher = teacherSrv.getTeacherById(teacherId);
        return teacher
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not Found"));
    }

    @PostMapping("")
    public Teacher addTeacher (@RequestBody Teacher teacher){
        return teacherSrv.addTeacher(teacher);
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher (@PathVariable String teacherId, @RequestBody Teacher teacher) {
        Optional<Teacher> updatedTeacher = teacherSrv.updateTeacher(teacherId, teacher);
        return updatedTeacher
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String teacherId) {
        if (teacherSrv.getTeacherById(teacherId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found");
        }
        teacherSrv.deleteTeacher(teacherId);
        return ResponseEntity.ok().build();
    }

}
