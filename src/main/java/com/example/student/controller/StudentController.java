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
        Optional<Student> student = Optional.ofNullable(studentSrv.getStudentById(studentId));
//        if (student == null) {
//            throw new StudentNotFoundException("id " + studentId);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
//        }
        return student.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"student not found"));
    }

    @PostMapping("")
    public Student addStudent(@RequestBody Student student) {
        return studentSrv.addStudent(student);
    }

    @PutMapping("/{studentId}")
//adding optional for null check
    public /*ResponseEntity<Student>*/ Student updateStudent(@PathVariable Long studentId, @RequestBody Student student){
        Optional<Student> updateStu = Optional.ofNullable(studentSrv.updateStudent(studentId, student));
//        if (updateStu== null) {
//            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"student not found");
//        }
//        return new ResponseEntity<>(updateStu, HttpStatus.OK);
        return updateStu.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"student not found"));
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
