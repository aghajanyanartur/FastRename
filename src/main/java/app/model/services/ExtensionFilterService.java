package app.model.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExtensionFilterService {

    public static List<File> filterByExtension(List<File> files, String extension) {
        List<File> filteredFiles = new ArrayList<>();
        for (File file : files) {
            if (getExtension(file).equals(extension)) {
                filteredFiles.add(file);
            }
        }
        return filteredFiles;
    }

    private static String getExtension(File file) {
        return file.getName().substring(file.getName().lastIndexOf(".")+1);
    }
}