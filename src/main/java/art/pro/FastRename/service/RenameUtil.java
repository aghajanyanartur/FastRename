package art.pro.FastRename.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public final class RenameUtil {

    public String test = ""; // FOR TESTING**********
    public String test2 = ""; // FOR TESTING**********

        public void renameToPattern(String directoryPath, String pattern, int startNumber, int incrementBy,
                int digitsNumber, boolean increment, boolean patternLeading, boolean sorted) {

            Path path = Paths.get(directoryPath);
            boolean hasReadAndExecutePermission = Files.isReadable(path) && Files.isExecutable(path);

            if (!hasReadAndExecutePermission) {
                changeToReadAndExecutePermission(directoryPath);
                test = "Changed permission for directory: " + directoryPath; // FOR TESTING**********
            }

            File[] files = new File(directoryPath).listFiles(File::isFile);

            if(files != null) {
                test = "The path is--: " + directoryPath + " ---- The folder path is not null -----"; // FOR TESTING**********
                if(sorted){
                    Arrays.sort(files, Comparator.comparing(File::getName));
                }
                renameFiles(files, directoryPath, pattern, startNumber, digitsNumber, incrementBy, increment, patternLeading);
            } else {
                test = "The path is--: " + directoryPath + " ---- The folder path was null -----"; // FOR TESTING**********
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
            test2 = "   @@@ Absolute path: " + file.getAbsolutePath() + "   Canonical path: " + file.getAbsolutePath();
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

    private void changeToReadAndExecutePermission(String directoryPath) {
        Path path = Paths.get(directoryPath);
        Set<PosixFilePermission> perms = new HashSet<>();
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        try {
            Files.setPosixFilePermissions(path, perms);
        } catch (UnsupportedOperationException e) {
            // Handle non-POSIX platforms
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
    }
}
