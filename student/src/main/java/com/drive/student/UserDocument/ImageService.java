package com.drive.student.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@Service
public class ImageService {
    @Autowired
    private ImageRepo repository;

    @Transactional
    public String uploadFiles(MultipartFile image, MultipartFile pdf, String studentEmail) throws IOException {
        ImageData existingData = repository.findByStudentEmail(studentEmail).orElse(new ImageData());
        existingData.setStudentEmail(studentEmail);

        if (image != null && image.getContentType().startsWith("image/")) {
            existingData.setImageData(ImageUtils.compressImage(image.getBytes()));
            existingData.setType_img(image.getContentType());
        }

        if (pdf != null && pdf.getContentType().equals("application/pdf")) {
            existingData.setPdf(pdf.getBytes());
            existingData.setType_pdf(pdf.getContentType());
        }

        repository.save(existingData);
        return "Files uploaded successfully";
    }

    @Transactional(readOnly = true)
    public byte[] downloadImage(String studentEmail) {
        Optional<ImageData> dbImageData = repository.findByStudentEmail(studentEmail);
        if (dbImageData.isPresent() && dbImageData.get().getImageData() != null) {
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        }
        throw new RuntimeException("Image not found for email: " + studentEmail);
    }

    @Transactional(readOnly = true)
    public byte[] downloadPdf(String studentEmail) {
        Optional<ImageData> dbImageData = repository.findByStudentEmail(studentEmail);
        if (dbImageData.isPresent() && dbImageData.get().getPdf() != null) {
            return dbImageData.get().getPdf();
        }
        throw new RuntimeException("PDF not found for email: " + studentEmail);
    }
}
