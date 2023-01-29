package com.example.student.controller;

import com.example.student.domain.Student;
import com.example.student.service.impl.StudentSrvImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
// TODO: using @RequestMapping at controller level to apply the same root  to all endpoints.
@RequestMapping("/students")
public class StudentController {

//    TODO: create a StudentService and inject the service here. The StudentService contains
//     private Map<Long, Student> students = new HashMap<>();
//    The controller should not responsible for the underlying mechanism of storing and retrieving students.

    private final StudentSrvImpl studentSrv;

    public StudentController(StudentSrvImpl studentSrv) {
        this.studentSrv = studentSrv;
    }

    @GetMapping("")
    public List<Student> getStudents(){
        return studentSrv.getStudents();
    }

    @GetMapping("/{studentId}")
//    TODO: when a student doesn't exist for a request_id, then return HttpStatus 404 (NotFound).
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
    //    TODO: when a student doesn't exist for a request_id, then return HttpStatus 404 (NotFound).
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student student){
        Student updateStu = studentSrv.updateStudent(studentId, student);
        if (updateStu== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateStu, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    //    TODO: when a student doesn't exist for a request_id, then return HttpStatus 404 (NotFound).
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        if(studentSrv.getStudentById(studentId)==null){
            return ResponseEntity.notFound().build();
        }
        studentSrv.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

}
