package com.drive.student.Application;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicantDTO {
    private Long id;
    private String studentName;
    private String studentEmail;
    private Application.ApplicationStatus status;
    private String stage;
    private String passNext;

    // Constructor to initialize fields
    public ApplicantDTO(Long id, String studentName, String studentEmail, Application.ApplicationStatus status, String stage,String passNext) {
        this.id = id;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.status = status;
        this.stage = stage;
        this.passNext = passNext;
    }
}
