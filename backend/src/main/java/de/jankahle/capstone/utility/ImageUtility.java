package de.jankahle.capstone.utility;

import java.util.List;

public class ImageUtility {
    private static final List<String> validTypes = List.of(
            "image/gif",
            "image/png",
            "image/jpg",
            "image/jpeg",
            "image/pjpeg"
    );

    public static boolean IsContentTypeValid(String contentType) {
        return !contentType.isEmpty() && validTypes.contains(validTypes);
    }
}
