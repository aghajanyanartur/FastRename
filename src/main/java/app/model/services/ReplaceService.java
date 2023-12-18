package app.model.services;

public class ReplaceService {

    public static String replace(String name, String find, String replace) {
        return name.replace(find, replace);
    }
}
