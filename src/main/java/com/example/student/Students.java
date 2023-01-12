package com.example.student;

// TODO: why the class user Students in plural form? Does this class represent one student or many students?
public class Students {
    private Long id;
    private String name;
    private int grade;

    public Students(Long id, String name, Integer grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
