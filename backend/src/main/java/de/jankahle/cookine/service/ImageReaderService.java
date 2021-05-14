package de.jankahle.cookine.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageReaderService {

    private Tesseract tesseract;

    public ImageReaderService() {
        initTesseract();
    }

    private void initTesseract() {
        tesseract = new Tesseract();
        tesseract.setLanguage("deu");
        tesseract.setOcrEngineMode(1);

        try {
            Path tessDataDirectory = Paths.get(ClassLoader.getSystemResource("tessdata").toURI());
            tesseract.setDatapath(tessDataDirectory.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String getTextFromImage(File image) {
        try {
            return tesseract.doOCR(image);
        } catch (TesseractException e) {
            return String.format("Error while reading image %s", e.getMessage());
        }
    }
}