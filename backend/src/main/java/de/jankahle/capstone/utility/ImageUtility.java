package de.jankahle.capstone.utility;

public class ImageUtility {
    private static final String[] validTypes = {
            "image/gif",
            "image/png",
            "image/jpg",
            "image/jpeg",
            "image/pjpeg"};

    public static boolean IsContentTypeValid(String contentType) {
        if (contentType.isEmpty())
            return false;
        for (String validType : validTypes) {
            if (contentType.equals(validType))
                return true;
        }
        return false;
    }
}
