package app.model.services;

public class CaseService {

    public static String changeCase(String name, String nameCase) {
        return switch (nameCase) {
            case "Lowercase" -> name.toLowerCase();
            case "Uppercase" -> name.toUpperCase();
            default -> name;
        };
    }
}
