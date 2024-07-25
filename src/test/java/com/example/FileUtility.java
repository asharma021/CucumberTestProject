package com.example;

import java.io.File;

public class FileUtility {

    public static void cleanDownloadsFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                deleteFile(file);
            }
        }
    }

    private static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    deleteFile(subFile);
                }
            }
        }
        file.delete();
    }
}
