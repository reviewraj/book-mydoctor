package com.bookmydoctor.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookmydoctor.entity.FileStorage;
import com.bookmydoctor.repository.FileStorageRepository;
import com.bookmydoctor.response.ResponseDto;
@Service
public class FileStorageService {
	private static final String STORAGE_PATH = "D:/mediafolder/";
	 private final FileStorageRepository fileStorageRepository;



	        public FileStorageService(FileStorageRepository fileStorageRepository) {
	            this.fileStorageRepository = fileStorageRepository;
	        }

	        public ResponseDto storeFile(MultipartFile file) throws IOException {
	            // Ensure the storage directory exists
	            File directory = new File(STORAGE_PATH);
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }

	            // Extract file details
	            String fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
	            String filePath = STORAGE_PATH + fileName;

	            // Save file to disk
	            try (InputStream fileContent = file.getInputStream();
	                 FileOutputStream fos = new FileOutputStream(filePath)) {
	                byte[] buffer = new byte[4096];
	                int bytesRead;
	                while ((bytesRead = fileContent.read(buffer)) != -1) {
	                    fos.write(buffer, 0, bytesRead);
	                }
	            }

	            // Store file details (only metadata and file path) in the database
	            FileStorage fileStorage = new FileStorage();
	            fileStorage.setFileName(fileName);
	            fileStorage.setFileType(file.getContentType());
	            fileStorage.setFilePath(filePath); // Save only the file path in DB

	            fileStorageRepository.save(fileStorage);

	            return new ResponseDto(false, "File uploaded successfully", null);
	        }
	    


	    public Optional<String> getFilePath(Long fileId) {
	    	return fileStorageRepository.findById(fileId).map(FileStorage::getFilePath);
	    }
}
