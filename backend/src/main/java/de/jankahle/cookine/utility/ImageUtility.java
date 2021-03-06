package de.jankahle.cookine.utility;

import java.util.List;

public class ImageUtility {
    private static final List<String> validTypes = List.of(
            "image/gif",
            "image/png",
            "image/jpg",
            "image/jpeg",
            "image/pjpeg"
    );

    public static boolean isContentTypeValid(String contentType) {
        return !contentType.isEmpty() && validTypes.contains(contentType);
    }
}
