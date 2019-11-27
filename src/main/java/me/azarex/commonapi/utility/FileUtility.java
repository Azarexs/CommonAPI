package me.azarex.commonapi.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtility {

    private FileUtility() {
        throw new UnsupportedOperationException("This is a utility class, and thus cannot be instantiated.");
    }

    /**
     * Checks if a file/directory exists at that path location, if not it creates it.
     * @param path Path of directory/file
     * @param replacement Replaces the path, with the current file
     * @return true if the file creation was successful, false if the creation had an error.
     */
    public static boolean createIfNotExists(Path path, InputStream replacement) {
        try {
            if (Files.notExists(path)) {
                if (Files.isDirectory(path)) Files.createDirectory(path);
                else Files.createFile(path);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }


}
