package com.example.alphaapi.service.impl;

import com.example.alphaapi.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private String uploadImageDirectory = "src/main/resources/static/images";

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path uploadPath = Path.of(uploadImageDirectory);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }

    @Override
    public byte[] viewFile(String fileName) throws IOException {
        Path filePath = Path.of(uploadImageDirectory, fileName);

        if (Files.exists(filePath)) {
            byte[] fileBytes = Files.readAllBytes(filePath);
            return fileBytes;
        } else {
            return null;
        }
    }

    @Override
    public String deleteFile(String fileName) throws IOException {
        Path imagePath = Path.of(uploadImageDirectory, fileName);

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
            return "Success";
        } else {
            return "Failed";
        }
    }
}
