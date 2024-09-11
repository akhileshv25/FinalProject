package com.drive.student.UserDocument;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentEmail; // Changed from studentId to studentEmail


    @Lob
    @Column(name = "Student_Image")
    private byte[] imageData;
    private String type_img;
    @Lob
    @Column(name = "ResumePdf")
    private byte[] pdf;
    private String type_pdf;
}
