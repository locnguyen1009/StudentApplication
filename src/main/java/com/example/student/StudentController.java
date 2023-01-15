package com.example.student;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    private Map<Long, Student> students = new HashMap<>();
    private Long count = 0L;

    @GetMapping("/students")
    public List<Student> getStudents(){
        return new ArrayList<>(students.values());
    }

    @GetMapping("students/{studentId}")
    public Student getStudentDetails (@PathVariable Long studentId) {
        return students.get(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        count++;
        student.setId(count);
        students.put(count, student);
        return student;
    }

    @PutMapping("/students/{studentId}")
    public String updateStudent(@PathVariable Long studentId, @RequestBody Student student){

        if(students.containsKey(studentId)) {
            student.setId(studentId);
            students.put(studentId, student);
            return "update student ID #" + studentId +" successfully";
        } else {
            return "Student ID #"+ studentId + " does not exist";

        }
    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        students.remove(studentId);
        return "student ID #" + studentId + " is deleted successfully";
    }
}
