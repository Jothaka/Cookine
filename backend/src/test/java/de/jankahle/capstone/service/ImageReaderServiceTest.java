package de.jankahle.capstone.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;


import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ImageReaderServiceTest {

    @Test
    void getTextFromImage() {
        //Given
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("example.png").getFile());

        ImageReaderService imageReaderService = new ImageReaderService();

        //When

        String actual = imageReaderService.getTextFromImage(file);

        //Then

        String expected = "Dies ist ein Beispieltext.\n";

        assertThat(actual, Matchers.is(expected));
    }
}