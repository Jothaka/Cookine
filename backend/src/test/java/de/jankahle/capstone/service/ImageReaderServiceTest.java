package de.jankahle.capstone.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;

class ImageReaderServiceTest {

    @DisplayName("The reading of the example.png should provide the correct String")
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