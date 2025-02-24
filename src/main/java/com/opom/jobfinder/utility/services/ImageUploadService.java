package com.opom.jobfinder.utility.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploadService{

        @Value("${file.upload-dir}")
        private String uploadDir;
        private static final String  folderName = "uploads";
        
        public String saveImage(MultipartFile file) throws Exception{

                Path uploadPath = Paths.get(folderName);

                if(!Files.exists(uploadPath)){
                        Files.createDirectories(uploadPath);
                }

                String filename =  file.getOriginalFilename();
                Path filePath = uploadPath.resolve(filename);
                Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
                
                return filePath.toString();
        }
}