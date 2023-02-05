package com.example.student.controller;

import com.example.student.domain.Student;
import com.example.student.service.impl.StudentSrvImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


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
    //Adding optional for null check
    @GetMapping("/{studentId}")
    public Student getStudentById (@PathVariable Long studentId) {
        Optional<Student> student = studentSrv.getStudentById(studentId);
        return student.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"student not found"));
    }

    @PostMapping("")
    public Student addStudent(@RequestBody Student student) {
        return studentSrv.addStudent(student);
    }

    @PutMapping("/{studentId}")

    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student student){
        Optional<Student> updatedStudent = studentSrv.updateStudent(studentId, student);
        return updatedStudent.map(st -> ResponseEntity.ok(st))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        if(studentSrv.getStudentById(studentId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        studentSrv.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

}
