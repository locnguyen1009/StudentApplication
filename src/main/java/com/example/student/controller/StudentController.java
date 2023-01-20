package com.example.student.controller;

import com.example.student.domain.Student;
import com.example.student.service.impl.StudentSrvImpl;
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
    public String updateStudent(@PathVariable Long studentId, @RequestBody Student student){

        return studentSrv.updateStudent(studentId, student);
    }

    @DeleteMapping("/{studentId}")
    //    TODO: when a student doesn't exist for a request_id, then return HttpStatus 404 (NotFound).
    public String deleteStudent(@PathVariable Long studentId) {
        return studentSrv.deleteStudent(studentId);
    }

}
