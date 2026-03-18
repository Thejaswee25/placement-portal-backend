package com.placementportal.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class FileUploadUtil {

    private final String uploadDir = "uploads/resumes"; // ✅ FIXED (hardcoded for consistency)

    public String saveFile(MultipartFile file, String username) throws IOException {

        // 🔥 Ensure directory exists
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 🔥 Clean username
        String sanitizedUsername = username.replaceAll("[^a-zA-Z0-9]", "_");

        // 🔥 Unique filename
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);

        String originalFilename = file.getOriginalFilename();
        String extension = (originalFilename != null && originalFilename.contains("."))
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : ".pdf";

        String fileName = sanitizedUsername + "_" + uniqueId + extension;

        // 🔥 Save file EXACTLY inside uploads/resumes
        Path targetLocation = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    public void deleteFile(String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(fileName);
        Files.deleteIfExists(filePath);
    }
}
