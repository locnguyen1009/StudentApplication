package com.example.student.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Course information contains information about the course. For example: Math101.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
  @Id
  private String id;

  private String name;
  private String description;
  private int credit;


}
