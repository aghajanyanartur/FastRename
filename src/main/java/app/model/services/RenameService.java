package app.model.services;

import app.model.components.dialogs.ErrorWindow;
import app.model.components.dialogs.WarningDialog;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RenameService {

    private String directoryPath;
    private String pattern;
    private int startNumber;
    private int incrementBy;
    private int digitsNumber;
    private boolean decrement;
    private boolean sorted;
    private String dateFormatSelection;
    private String dateFormatPattern;
    private String filterExtension;
    private boolean filterByExtension;
    private boolean keepCurrentName;
    private String nameCase;
    private String find;
    private String replace;
    private boolean changeExtension;
    private String newExtension;

    private List<File> currentFiles;
    private String currentName = "";
    private int serialNumber;
    private String formattedSerialNumber;
    private String date;
    private String extension;

    public RenameService(FormData formData) {
        this.directoryPath = formData.getDirectoryPath();
        this.pattern = formData.getPattern();
        this.startNumber = formData.getStartNumber();
        this.incrementBy = formData.getIncrementBy();
        this.digitsNumber = formData.getDigitsNumber();
        this.decrement = formData.isDecrement();
        this.sorted = formData.isSorted();
        this.dateFormatSelection = formData.getDateFormatSelection();
        this.dateFormatPattern = formData.getDateFormatPattern();
        this.filterExtension = formData.getExtension();
        this.filterByExtension = formData.isFilterByExtension();
        this.keepCurrentName = formData.isKeepCurrentName();
        this.nameCase = formData.getNameCase();
        this.find = formData.getFind();
        this.replace = formData.getReplace();
        this.changeExtension = formData.isChangeExtension();
        this.newExtension = formData.getNewExtension();

        this.serialNumber = startNumber;
    }

    public void rename() {
        if (!getAllFiles()) {
            return;
        }
        if(pattern.equals("")) {
            new ErrorWindow("Please, enter a pattern!").show();
            return;
        }
        if(!pattern.contains("{counter}")) {
            WarningDialog dialog = new WarningDialog("Consider adding counter to avoid naming collapse");
            Optional<ButtonType> result = dialog.showAndWait();

            // Check which button was clicked
            if (result.isPresent() && result.get().equals(dialog.getDismissButtonType())) {
                return;
            }
        }

        filteredByExtensionFiles();
        sort();

        for (File file : currentFiles) {
            if (keepCurrentName) {
                var fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
                currentName = CaseService.changeCase(fileName, nameCase);
                currentName = ReplaceService.replace(currentName, find, replace);
            }

            formattedSerialNumber = String.format("%0" + digitsNumber + "d", serialNumber);
            date = DateService.getFormattedDate(file, dateFormatSelection, dateFormatPattern);

            if (changeExtension) {
                extension = newExtension;
                System.out.println("Extension: " + extension);
            } else {
                extension = file.getName().substring(file.getName().lastIndexOf("."));
            }

            String newFileName = PatternConvertService.convert(pattern, currentName, date, formattedSerialNumber, extension);

            File newFile = new File(directoryPath + "\\" + newFileName);
            file.renameTo(newFile);

            if (decrement) {
                serialNumber -= incrementBy;
            } else {
                serialNumber += incrementBy;
            }
        }
    }

    public void sort() {
        if (sorted) {
            currentFiles = SortService.sort(currentFiles);
        }
    }

    private void filteredByExtensionFiles() {
        if (filterByExtension) {
            currentFiles = ExtensionFilterService.filterByExtension(currentFiles, filterExtension);
        }
    }

    private boolean getAllFiles() {
        File path = new File(directoryPath);
        if (!path.exists()) {
            new ErrorWindow("Invalid directory path!").show();
            return false;
        } else {
            File[] files = path.listFiles();
            if (files == null) {
                new ErrorWindow("Couldn't find files in the given directory.").show();
                return false;
            }
            List<File> fileList = new ArrayList<>();
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                }
            }
            currentFiles = fileList;
            return true;
        }
    }
}
