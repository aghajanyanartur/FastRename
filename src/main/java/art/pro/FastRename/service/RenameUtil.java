package art.pro.FastRename.service;

import java.io.File;
import java.util.*;

public final class RenameUtil {

        public void renameToPattern(String directoryPath, String pattern, int startNumber, int incrementBy,
                int digitsNumber, boolean increment, boolean patternLeading, boolean sorted) {

            File path = new File(directoryPath);
            File[] files = path.listFiles();
            if(files != null) {
                if(sorted){
                    Arrays.sort(files, Comparator.comparing(File::getName));
                }
                renameFiles(files, directoryPath, pattern, startNumber, digitsNumber, incrementBy, increment, patternLeading);
            }
        }

    private void renameFiles(File[] files, String directoryPath, String pattern, int startNumber,
            int digitsNumber, int incrementBy, boolean increment, boolean patternLeading) {

        int serialNumber = startNumber;
        for (File file : files) {
            String extension = file.getName().substring(file.getName().lastIndexOf("."));
            String newFileName = generateNewName(patternLeading, digitsNumber, pattern, serialNumber, extension);
            File newFile = new File(directoryPath + "\\" + newFileName);
            file.renameTo(newFile);
            serialNumber += increment ? incrementBy : -incrementBy;
        }
    }

    private String generateNewName(boolean patternLeading, int digitsNumber, String pattern, int serialNumber, String extension) {
        String newFileName;
        if(patternLeading) {
            newFileName = String.format("%s%0" + digitsNumber + "d%s", pattern, serialNumber, extension);
        } else {
            newFileName = String.format("%0" + digitsNumber + "d%s%s", serialNumber, pattern, extension);
        }
        return newFileName;
    }
}