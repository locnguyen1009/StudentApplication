package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.request.StudentReq;
import com.example.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentSrv;

    public StudentController(StudentService studentSrv) {
        this.studentSrv = studentSrv;
    }

    @GetMapping("")
    public List<Student> getStudents(){
        return studentSrv.getStudents();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById (@PathVariable String studentId) {
        Optional<Student> student = studentSrv.getStudentById(studentId);
        return student.map(ResponseEntity::ok)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"student not found"));
    }

    @PostMapping("")
    public Student addStudent(@RequestBody StudentReq studentReq) {
        // convert StudentReq -> student
        return studentSrv.addStudent(studentReq);
    }

    @PutMapping("/{studentId}")

    public ResponseEntity<Student> updateStudent(@PathVariable String studentId, @RequestBody Student student){
        Optional<Student> updatedStudent = studentSrv.updateStudent(studentId, student);
        return updatedStudent
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentId) {
        if(studentSrv.getStudentById(studentId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        studentSrv.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

}
