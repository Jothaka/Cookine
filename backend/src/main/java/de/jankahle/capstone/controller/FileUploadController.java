package de.jankahle.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/upload")
public class FileUploadController {

    @Autowired
    public FileUploadController() {
    }

    @PostMapping
    public  String handleFileUpload(@RequestParam("file")MultipartFile file) {

        if(file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        return "Upload successful";
    }
}
