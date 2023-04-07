package art.pro.FastRename.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public final class RenameUtil {

    public String test = ""; // FOR TESTING**********
    public String test2 = ""; // FOR TESTING**********

        public void renameToPattern(String directoryPath, String pattern, int startNumber, int incrementBy,
                int digitsNumber, boolean increment, boolean patternLeading, boolean sorted) {

//            *************************** try with move
            Path filePath = Paths.get(directoryPath);
            Path newPath = Paths.get("C:\\Users\\Lenovo\\Desktop\\testfolderforrenameapp\\newPath.txt");
            test += "BEFORE:: filePath = " + filePath + ",,,  newPath = " + newPath + "||||";
            try{
                Files.move(filePath, newPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            test += "AFTER:: filePath = " + filePath + ",,,  newPath = " + newPath + "||||";

//            *************************** Try with permissions
            File file = new File(directoryPath);
            test += "THE FILE IS >>>> " + file.getName() + "::::   ";
            file.setReadable(true, false);
            file.setWritable(true, false);
            file.setExecutable(true, false);
            File newFile = new File("C:\\Users\\Lenovo\\Desktop\\testfolderforrenameapp\\newFile.txt");
            file.renameTo(newFile);
            test += "THE FILE IS >>>> " + file.getName() + "::::   ";
//            ***************************

//            ********************************
//            TRY TO MAKE NEW FILE AND DIRECTORY

            // ************ directory **********
            File newDirectory1 = new File("C:\\Users\\Lenovo\\Desktop\\newDirectory1.txt");
            newDirectory1.mkdir();
            File newDirectory2 = new File("C:/User/Lenovo/Desktop/newDirectory2");
            newDirectory2.mkdir();

            test += "NEW DIRECTORIES = " + newDirectory1 + "  --and--  " + newDirectory2;

            // ************ file ***************
            File newlyCreatedFile1 = new File("C:\\Users\\Lenovo\\Desktop\\testfolderforrenameapp\\newlyCreatedFile1.txt");
            try {
                newlyCreatedFile1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            File newlyCreatedFile2 = new File("C:/User/Lenovo/Desktop/testfolderforrenameapp/newlyCreatedFile2.txt");
            try {
                newlyCreatedFile2.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            test += "NEW FILES = " + newlyCreatedFile1 + "  --and--  " + newlyCreatedFile2;
//            TRY TO MAKE NEW FILE AND DIRECTORY
//            *********************************

            File path = new File(directoryPath);

            File[] files = path.listFiles();

            if(files != null) {
                test += "The path is--: " + directoryPath + " ---- The folder path is not null -----"; // FOR TESTING**********
                if(sorted){
                    Arrays.sort(files, Comparator.comparing(File::getName));
                }
//                renameFiles(files, directoryPath, pattern, startNumber, digitsNumber, incrementBy, increment, patternLeading);
            } else {
                test += "The path is--: " + directoryPath + " ---- The folder path was NULL -----"; // FOR TESTING**********
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
}
