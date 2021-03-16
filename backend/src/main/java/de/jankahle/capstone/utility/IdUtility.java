package de.jankahle.capstone.utility;

import java.util.UUID;

public class IdUtility {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
