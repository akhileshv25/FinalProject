package com.drive.student.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ImageController {

    @Autowired
    private ImageService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "pdf", required = false) MultipartFile pdf,
            @RequestParam("email") String studentEmail) throws IOException {
        String uploadMessage = service.uploadFiles(image, pdf, studentEmail);
        return ResponseEntity.status(HttpStatus.OK).body(uploadMessage);
    }

    @GetMapping("/download/image/{studentEmail}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String studentEmail) {
        byte[] imageData = service.downloadImage(studentEmail);
        String contentType = "image/png"; // Adjust if needed
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(contentType))
                .body(imageData);
    }

    @GetMapping("/download/pdf/{studentEmail}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String studentEmail) {
        byte[] pdfData = service.downloadPdf(studentEmail);
        String contentType = "application/pdf"; // PDF content type
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(contentType))
                .body(pdfData);
    }
}
