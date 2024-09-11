package com.drive.student.UserDocument;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ImageRepo extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByStudentEmail(String studentEmail); // Corrected method name
}
