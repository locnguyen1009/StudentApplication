package com.example.student.domain;

import org.springframework.data.annotation.Id;

public class Student {
    @Id
    private String id;
    private String name;
    private int grade;

    public Student(String name, Integer grade) {

        this.name = name;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
