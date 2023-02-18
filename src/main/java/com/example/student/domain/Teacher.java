package com.example.student.domain;

import org.springframework.data.annotation.Id;
import java.util.List;

public class Teacher {

  @Id
  private String id;

  private String firstName;
  private String lastName;
  /**
   * A list of CourseId that the teacher can lecture
   */
  private List<Long> specializedCourses;

  public Teacher(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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
