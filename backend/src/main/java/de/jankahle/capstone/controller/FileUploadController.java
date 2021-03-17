package de.jankahle.capstone.controller;

import de.jankahle.capstone.utility.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/upload")
public class FileUploadController {

    @PostMapping
    public  String handleFileUpload(@RequestParam("file")MultipartFile file) {

        if(file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"File is empty");
        }

        if(ImageUtility.IsContentTypeValid(file.getContentType())) {
            return  "Upload successful";
        }
        return "Wrong filetype";
    }
}
