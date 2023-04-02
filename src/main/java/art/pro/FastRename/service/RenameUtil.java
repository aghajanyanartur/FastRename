package art.pro.FastRename.service;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public final class RenameUtil {

    public void renameToPattern(
            String directoryPath, String pattern, int startNumber, int incrementBy,
            int digitsNumber, boolean increment, boolean patternLeading, boolean sorted) {

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if(files == null) {
            return;
        }

        if(sorted){
            Arrays.sort(files, Comparator.comparing(File::getName));
        }

        int serialNumber = startNumber;
        String newFileName;
        for (File file : files) {
            if (file.isFile()) {
                String extension = file.getName().substring(file.getName().lastIndexOf("."));
                if(patternLeading) {
                    newFileName = String.format("%s%0" + digitsNumber + "d%s", pattern, serialNumber, extension);
                } else {
                    newFileName = String.format("%0" + digitsNumber + "d%s%s", serialNumber, pattern, extension);
                }
                File newFile = new File(directoryPath + "\\" + newFileName);
                file.renameTo(newFile);
                serialNumber += increment ? incrementBy : -incrementBy;
            }
        }
    }
}
