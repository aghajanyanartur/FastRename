package app.model.services;

public class PatternConvertService {

    public static String convert(String pattern, String currentName, String date, String formattedSerialNumber, String extension) {
        return pattern.replace("{current_name}", currentName)
                .replace("{date}", date)
                .replace("{counter}", formattedSerialNumber) + extension;
    }
}
