package com.example.student;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// TODO: using @RequestMapping at controller level to apply the same root /students to all endpoints.
public class StudentController {

//    TODO: create a StudentService and inject the service here. The StudentService contains
//     private Map<Long, Student> students = new HashMap<>();
//    The controller should not responsible for the underlying mechanism of storing and retrieving students.
    private Map<Long, Student> students = new HashMap<>();
    private Long count = 0L;

    @GetMapping("/students")
    public List<Student> getStudents(){
        return new ArrayList<>(students.values());
    }

    @GetMapping("students/{studentId}")
//    TODO: when a student doesn't exist for a request_id, then return HttpStatus 4040 (NotFound).
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
//            TODO: question: is it necessary to set studentId here?
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
