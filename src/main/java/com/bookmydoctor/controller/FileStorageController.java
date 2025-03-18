package com.bookmydoctor.controller;

import com.bookmydoctor.response.ResponseDto;
import com.bookmydoctor.service.FileStorageService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FileStorageController {

    private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseDto uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            return fileStorageService.storeFile(file);
        } catch (IOException e) {
            return new ResponseDto(true, "File upload failed: " + e.getMessage(), null);
        }
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable Long fileId) {
        Optional<String> filePathOpt = fileStorageService.getFilePath(fileId);
        
        if (filePathOpt.isPresent()) {
            File file = new File(filePathOpt.get());
            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found on server.");
            }

            FileSystemResource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found in database.");
        }
    }
}
