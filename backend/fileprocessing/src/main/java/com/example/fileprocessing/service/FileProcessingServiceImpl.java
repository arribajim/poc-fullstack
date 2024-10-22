package com.example.fileprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class FileProcessingServiceImpl implements FileProcessingService {

    @Autowired
    private NotificationService notificationService;

    @Value("${filePath}")
    private String basePath;

    @Override
    public List<String> fileList() {
        File dir = new File(basePath);
        File[] files = dir.listFiles();

        return files != null ? Arrays.stream(files).map(File::getName).collect(Collectors.toList()) : null;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        Path path = Path.of(basePath + multipartFile.getOriginalFilename());

        try {
            log.info("Creating file "+ path.getFileName());
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            notificationService.sendLinkForFillForm(multipartFile.getName());
            return "CREATED";
        } catch (Exception e) {
            log.error("NOT CREATED "+ e.getMessage(),e);
        }
        return "FAILED";
    }

   

    @Override
    public Resource downloadFile(String fileName) {
        File dir = new File(basePath + fileName);
        try {
            if (dir.exists()) {
                return new UrlResource(dir.toURI());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

}
