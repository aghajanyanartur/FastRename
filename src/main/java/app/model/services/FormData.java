package app.model.services;

public class FormData {

    private String directoryPath;
    private String pattern;
    private int startNumber;
    private int incrementBy;
    private int digitsNumber;
    private boolean decrement;
    private boolean sorted;
    private String dateFormatSelection;
    private String dateFormatPattern;
    private String extension;
    private boolean filterByExtension;
    private boolean keepCurrentName;
    private String nameCase;
    private String find;
    private String replace;
    private boolean changeExtension;
    private String newExtension;

    public String getNewExtension() {
        return "." + newExtension;
    }

    public void setNewExtension(String newExtension) {
        this.newExtension = newExtension;
    }

    public boolean isChangeExtension() {
        return changeExtension;
    }

    public void setChangeExtension(boolean changeExtension) {
        this.changeExtension = changeExtension;
    }

    public boolean isKeepCurrentName() {
        return keepCurrentName;
    }

    public void setKeepCurrentName(boolean currentName) {
        this.keepCurrentName = currentName;
    }

    public String getNameCase() {
        return nameCase;
    }

    public void setNameCase(String nameCase) {
        this.nameCase = nameCase;
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    public boolean isFilterByExtension() {
        return filterByExtension;
    }

    public void setFilterByExtension(boolean filterByExtension) {
        this.filterByExtension = filterByExtension;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getIncrementBy() {
        return incrementBy;
    }

    public void setIncrementBy(int incrementBy) {
        this.incrementBy = incrementBy;
    }

    public int getDigitsNumber() {
        return digitsNumber;
    }

    public void setDigitsNumber(int digitsNumber) {
        this.digitsNumber = digitsNumber;
    }

    public boolean isDecrement() {
        return decrement;
    }

    public void setDecrement(boolean decrement) {
        this.decrement = decrement;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public String getDateFormatSelection() {
        return dateFormatSelection;
    }

    public void setDateFormatSelection(String dateFormatSelection) {
        this.dateFormatSelection = dateFormatSelection;
    }

    public String getDateFormatPattern() {
        return dateFormatPattern;
    }

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
