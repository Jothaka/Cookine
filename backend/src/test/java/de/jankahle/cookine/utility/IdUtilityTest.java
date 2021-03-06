package de.jankahle.cookine.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdUtilityTest {

    @Tag("Unit")
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