package com.reliablesystems.doctoroffice.core.utils.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileUtil {

    private FileUtil() {}

    /**
     * Method to create a directory if not exist
     *
     * @param directory Directory name
     */
    public static boolean createFolderIfNotExist(String directory) {
        File file = new File(directory);
        // If not exist create
        if (!file.exists()){
            return file.mkdirs();
        }
        return true;
    }

    /**
     * Method to create a file in a specific path
     *
     * @param directoryFile Directory file
     * @param file The file
     */
    public static void uploadFile(String directoryFile, byte[] file) {
        Path path = Paths.get(directoryFile);
        try {
            Files.write(path, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Delete file of a directory
     *
     * @param pathFile Directory
     */
    public static boolean deleteFileByPath(String pathFile) {
        File file = new File(pathFile);
        return file.delete();
    }
}
