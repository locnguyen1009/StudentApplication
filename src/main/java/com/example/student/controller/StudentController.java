package com.example.student.controller;

import com.example.student.domain.Student;
import com.example.student.service.impl.StudentSrvImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentSrvImpl studentSrv;

    public StudentController(StudentSrvImpl studentSrv) {
        this.studentSrv = studentSrv;
    }

    @GetMapping("")
    public List<Student> getStudents(){
        return studentSrv.getStudents();
    }

    @GetMapping("/{studentId}")
    public Student getStudentById (@PathVariable Long studentId) {
        Student student = studentSrv.getStudentById(studentId);
        if (student == null) {
            throw new StudentNotFoundException("id " + studentId);
        }
        return student;
    }

    @PostMapping("")
    public Student addStudent(@RequestBody Student student) {
        return studentSrv.addStudent(student);
    }

    @PutMapping("/{studentId}")

    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student student){
        Student updateStu = studentSrv.updateStudent(studentId, student);
        if (updateStu== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateStu, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        if(studentSrv.getStudentById(studentId)==null){
            return ResponseEntity.notFound().build();
        }
        studentSrv.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

}
