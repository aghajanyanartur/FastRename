package app.model.services;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortService {

    public static List<File> sort(List<File> files) {
        return files.stream()
                .sorted(Comparator.comparing(File::getName))
                .collect(Collectors.toList());
    }
}
