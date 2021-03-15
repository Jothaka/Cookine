package de.jankahle.capstone.utility;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.annotation.Id;

import static org.junit.jupiter.api.Assertions.*;

class IdUtilityTest {

    @Test
    @DisplayName("Generating an ID should always result in different IDs")
    void generateId() {

        //When
        String firstID = IdUtility.generateId();
        String secondID = IdUtility.generateId();

        //Then
        assertNotEquals(firstID, secondID);
    }
}