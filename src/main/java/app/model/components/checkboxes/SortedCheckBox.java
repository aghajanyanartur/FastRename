package app.model.components.checkboxes;

import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;

public class SortedCheckBox extends CheckBox {

    public SortedCheckBox() {
        super("Sorted");
        setFont(Font.font("Arial", 16));

    }

    public boolean isSorted() {
        return isSelected();
    }
}