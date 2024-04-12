package com.inkhyang.documentservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Service
public class DocumentService implements Uploader {
    @Value("${upload.path}")
    private String uploadPath;
    @Override
    public String upload(MultipartFile file) {
        try {
            String resultFilename = null;
            if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                resultFilename = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));

            }
            return resultFilename;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String file){
        try {
            Files.deleteIfExists(Path.of(uploadPath, file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
