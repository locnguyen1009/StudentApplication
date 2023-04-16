package com.example.student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
  @Id
  private String id;

  private String firstName;
  private String lastName;
  /**
   * A list of CourseId that the teacher can lecture
   */
  private List<String> specializedCourses;


}
