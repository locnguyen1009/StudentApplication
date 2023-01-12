package com.example.student;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    private Map<Long, Students> students = new HashMap<>();
    private Long count = 0L;

    @GetMapping("/students")
    public List<Students> getStudents(){
        return new ArrayList<>(students.values());
    }

    @PostMapping("/addstudent")
    public Students addStudent(@RequestBody Students student) {
        count++;
        student.setId(count);
        students.put(count, student);
        return student;
    }

    @PutMapping("/updatestudent")
    public Students updateStudent(Long studentId, @RequestBody Students student){
        student.setId(studentId);
        students.put(studentId, student);
        return student;
    }

    @DeleteMapping("/deletestudent")
    public Students deleteStudent(Long studentId) {
        return null;
    }
}
