package com.example.student.domain;


import java.util.Date;
import java.util.List;

/**
 * A class where a teacher teaches a specific course (referred to by courseId) to a list of students.
 *
 */
public class ClassCourse {

  private Long id;

  private String courseId;
  private Long teacherId;

  private List<Long> studentIds;
  private Date begin;
  private Date end;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public Long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Long teacherId) {
    this.teacherId = teacherId;
  }



}
