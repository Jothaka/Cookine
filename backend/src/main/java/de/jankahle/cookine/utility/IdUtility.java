package de.jankahle.cookine.utility;

import java.util.UUID;

public class IdUtility {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
