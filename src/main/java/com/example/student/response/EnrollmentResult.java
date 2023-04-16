package com.example.student.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//todo: boolean for status

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResult {
    private String enrollmentId;
    private boolean enrollStatus;
    private String message;

}
