package com.example.student.domain;

import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;

public class Student {
    @Id

    private Long id;
    private String name;
    private int grade;

    public Student(Long id, String name, Integer grade) {

        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
