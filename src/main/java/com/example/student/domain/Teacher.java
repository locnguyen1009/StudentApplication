package com.example.student.domain;

import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.processing.Generated;
import java.util.List;

public class Teacher {


  private Long id;

  private String firstName;
  private String lastName;
  /**
   * A list of CourseId that the teacher can lecture
   */
  private List<Long> specializedCourses;

  public Teacher(Long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
