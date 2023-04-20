package com.istad.springthymleafpart1.service.serviceImpl;

import com.istad.springthymleafpart1.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileUploadServiceImp implements FileUploadService {
    Path fileLocationStorage;
    FileUploadServiceImp(){
        fileLocationStorage = Path.of("src/main/resources/images");

    }
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();


        if (filename!=null){
            //condition for filename
            if(filename.contains("..")){
                System.out.println("Filename is incorrect!!");
                return null;
            }
            String[] fileParts = filename.split("\\.");
            filename = UUID.randomUUID()+"."+fileParts[1];

            Path resolvePath = fileLocationStorage.resolve(filename);
            Files.copy(file.getInputStream(),resolvePath, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }else return null;

    }
}
