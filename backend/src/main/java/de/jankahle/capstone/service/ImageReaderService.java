package de.jankahle.capstone.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ImageReaderService {

    public String getTextFromImage(File image) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata");
        tesseract.setLanguage("deu");
        try {
            return tesseract.doOCR(image);
        }
        catch (TesseractException e) {
            return  String.format("Error while reading image %s", e.getMessage());
        }
    }
}
