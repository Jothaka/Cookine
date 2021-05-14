package de.jankahle.cookine.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtility {
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    public static File convertMultipartFileToFile(MultipartFile file) throws IOException {
        InputStream stream = file.getInputStream();
        File temporaryFile = new File("temp.png");
        FileUtility.copyInputStreamToFile(stream, temporaryFile);
        return temporaryFile;
    }

    public static void copyInputStreamToFile(InputStream inputStream, File temporaryFile) throws IOException {
        try(FileOutputStream outputStream = new FileOutputStream(temporaryFile, false)){
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,read);
            }
        }
    }
}
