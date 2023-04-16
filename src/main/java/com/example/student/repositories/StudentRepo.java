package com.example.student.repositories;

import com.example.student.entity.Student;

import java.util.List;
import java.util.Optional;


// TODO: define the method that use for CRUD operation here
public interface StudentRepo {
    /**
     * Find the student with given id.
     * @param id - studentId
     * @return Optional of Student if the student exists, otherwise return optional of empty
     */
    Optional<Student> getStudentById(String id);

    /**
     * Adding student
     * @param student
     * @return Student
     */
    Student addStudent (Student student);

    /**
     * update student with given id
     * @param id
     * @param student
     * @return Optional of updated student if student exists, otherwise return optional of empty
     */
    Optional<Student> updateStudent (String id, Student student);

    /**
     * delete student with given id
     * @param id
     */
    void deleteStudent (String id);

    List<Student> getStudents();
}
