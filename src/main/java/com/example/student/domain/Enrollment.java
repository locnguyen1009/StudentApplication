package com.example.student.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.*;

/**
 * A class where a teacher teaches a specific course (referred to by courseId) to a list of students.
 *
 */

// todo: write enrollment with enrollment controller. link courseID, teacherID from "Course class" and "teacherID"
// TODO: write enrollment controller that allow admin to:
//   create new enrollment (with existing courseID)
//  Update enrollment with teacherID (assign a teacher to course).
//  GET enrollment by enrollmentID should return enrollment including course and teacher
//   Teacher - Name, Teacher ID
//   Course - Name, ID, credit,

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
  @Id
  private String id;
  private Course courseId;
  private Teacher teacherId;
  private Integer maxCapacity;
  private List<Student> studentIds = new ArrayList<>();

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date enrollDeadline;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date begin;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date end;


}
