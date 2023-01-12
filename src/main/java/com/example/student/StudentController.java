package com.example.student;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
// TODO: when design endpoints, pay attention on the English verb before the endpoint. GET, POST, PUT, DELETE already have their meanings, don't repeat.
//    Example: DELETE /deletestudent -> bad.
    private Map<Long, Students> students = new HashMap<>();
    private Long count = 0L;

    // TODO: rewrite endpoint by Microsoft documents.
    @GetMapping("/students")
    public List<Students> getStudents(){
        return new ArrayList<>(students.values());
    }


    @PostMapping("/addstudent")
    // TODO: rewrite endpoint by Microsoft documents.
    public Students addStudent(@RequestBody Students student) {
        count++;
        student.setId(count);
        students.put(count, student);
        return student;
    }

    @PutMapping("/updatestudent")
    // TODO: rewrite endpoint by Microsoft documents.
    //    TODO: Learn @PathVariale and use it here
//    TODO: when put, User must send the student Id to update.

    public Students updateStudent(Long studentId, @RequestBody Students student){
        student.setId(studentId);
        students.put(studentId, student);
        return student;
    }

    @DeleteMapping("/deletestudent")
    // TODO: rewrite endpoint by Microsoft documents.
//    TODO: Learn @PathVariale and use it here
    public Students deleteStudent(Long studentId) {
        return null;
    }
}
