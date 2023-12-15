package app.model;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

public final class RenameUtil {

    public static void renameToPattern(String directoryPath, String pattern, int startNumber, int incrementBy,
                                       int digitsNumber, boolean increment, boolean patternLeading, boolean sorted,
                                       String dateFormatSelection, String dateFormatPattern) {

        File path = new File(directoryPath);
        File[] files = path.listFiles();
        if (files != null) {
            if (sorted) {
                Arrays.sort(files, Comparator.comparing(File::getName));
            }
            renameFiles(files, directoryPath, pattern, startNumber, digitsNumber, incrementBy, increment,
                    patternLeading, dateFormatSelection, dateFormatPattern);
        }
    }

    private static void renameFiles(File[] files, String directoryPath, String pattern, int startNumber,
                                    int digitsNumber, int incrementBy, boolean increment, boolean patternLeading,
                                    String dateFormatSelection, String dateFormatPattern) {

        int serialNumber = startNumber;
        for (File file : files) {
            String extension = file.getName().substring(file.getName().lastIndexOf("."));
            String datePart = getFormattedDate(file, dateFormatSelection, dateFormatPattern);
            String newFileName = generateNewName(patternLeading, digitsNumber, pattern, serialNumber, extension, datePart);
            File newFile = new File(directoryPath + "\\" + newFileName);
            file.renameTo(newFile);
            serialNumber += increment ? incrementBy : -incrementBy;
        }
    }

    private static String getFormattedDate(File file, String dateFormatSelection, String dateFormatPattern) {
        String formattedDate = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);

        try {
            Path filePath = file.toPath();
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);

            switch (dateFormatSelection) {
                case "Creation Date":
                    formattedDate = attrs.creationTime()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .format(formatter);
                    break;
                case "Last Modified Date":
                    formattedDate = attrs.lastModifiedTime()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .format(formatter);
                    break;
                case "Current Date":
                    formattedDate = LocalDateTime.now().format(formatter);
                    break;
                case "No Date":
                default:
                    // No date is appended
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedDate;

    }

    private static String generateNewName(boolean patternLeading, int digitsNumber, String pattern, int serialNumber,
                                          String extension, String datePart) {
        String newFileName;
        if (patternLeading) {
            newFileName = String.format("%s%0" + digitsNumber + "d%s%s", pattern, serialNumber, datePart, extension);
        } else {
            newFileName = String.format("%0" + digitsNumber + "d%s%s%s", serialNumber, pattern, datePart, extension);
        }
        return newFileName;
    }
}