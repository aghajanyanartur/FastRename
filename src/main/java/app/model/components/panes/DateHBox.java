package app.model.components.panes;

import app.model.constraints.Constraints;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DateHBox extends HBox {

    private Label label;
    private ComboBox<String> comboBox;
    private ComboBox<String> comboBoxPattern;

    public DateHBox() {
        label = new Label("Add date:");
        label.setFont(Constraints.FONT);

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("No Date", "Creation Date", "Last Modified Date", "Current Date");
        comboBox.setValue("No Date");

        comboBoxPattern = new ComboBox<>();
        comboBoxPattern.getItems().addAll("yyyyMMdd", "yyyy-MM-dd", "yyyy.MM.dd", "yyyy_MM_dd",
                "MMddyyyy", "MM-dd-yyyy", "MM.dd.yyyy", "MM_dd_yyyy",
                "ddMMyyyy", "dd-MM-yyyy", "dd.MM.yyyy", "dd_MM_yyyy");
        comboBoxPattern.setValue("yyyyMMdd");

        setSpacing(20);
        setBorder(Constraints.BORDER);
        setPadding(new Insets(10, 10, 10, 10));
        getChildren().addAll(label, comboBox, comboBoxPattern);
    }

    public String getDateFormat() {
        return comboBox.getValue();
    }

    public void setDateFormat(String dateFormat) {
        comboBox.setValue(dateFormat);
    }

    public String getDateFormatPattern() {
        return comboBoxPattern.getValue();
    }

    public void setDateFormatPattern(String dateFormatPattern) {
        comboBoxPattern.setValue(dateFormatPattern);
    }
}
