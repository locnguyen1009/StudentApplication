package com.example.student.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherReq {
    private String firstName;
    private String lastName;
    @DocumentReference
    private List<String> specializedCourses;
}
