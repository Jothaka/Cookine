package de.jankahle.capstone.controller;

import de.jankahle.capstone.service.ImageReaderService;
import de.jankahle.capstone.utility.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("api/upload")
public class FileUploadController {

    private final ImageReaderService imageReaderService;

    @Autowired
    public FileUploadController(ImageReaderService imageReaderService) {
        this.imageReaderService = imageReaderService;
    }

    @PostMapping
    public  String handleFileUpload(@RequestParam("file")MultipartFile file) {

        if(file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"File is empty");
        }

        if(ImageUtility.IsContentTypeValid(file.getContentType())) {
            try {
                File fileResource = file.getResource().getFile();
                return imageReaderService.getTextFromImage(fileResource);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  "Upload successful but empty file";
        }
        return "Wrong filetype";
    }
}
