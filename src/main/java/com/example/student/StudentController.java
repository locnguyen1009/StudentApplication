package com.example.student;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    private static Map<Long, Students> students = new HashMap<>();
    private static Long count = 2L;

    static Students student1 = new Students(1L,"Loc", 90);

    static {
        Students student1 = new Students(1L, "Alex", 90);
        Students student2 = new Students(2L,"John", 80);

        students.put(1L,student1);
        students.put(2L,student2);
    }

    @GetMapping("/students")
    public static List<Students> getStudents(){
        return new ArrayList<>(students.values());
    }

    @PostMapping("/addstudent")
    public static Students addStudent(Students student) {
        count++;
        student.setId(count);
        students.put(count, student);
        return student;
    }

    @PutMapping("/updatestudent")
    public static Students updateStudent(Long studentId, Students student){
        return null;
    }

    @DeleteMapping("/deletestudent")
    public static Students deleteStudent(Long studentId) {
        return null;
    }
}
