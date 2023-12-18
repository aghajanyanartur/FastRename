package app.model.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateService {

    public static String getFormattedDate(File file, String dateFormatSelection, String dateFormatPattern) {
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
}
