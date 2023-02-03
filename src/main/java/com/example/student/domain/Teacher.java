package com.example.student.domain;

import java.util.List;

public class Teacher {

  private Long id;

  private String firstName;
  private String lastName;

  /**
   * A list of CourseId that the teacher can lecture
   */
  private List<Long> specializedCourses;
}
