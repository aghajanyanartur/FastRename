package app.model.components.checkboxes;

import app.model.constraints.Constraints;
import javafx.scene.control.CheckBox;

public class SortedCheckBox extends CheckBox {

    public SortedCheckBox() {
        super("Sort alphabetically before renaming");
        setFont(Constraints.FONT);

    }

    public boolean isSorted() {
        return isSelected();
    }

    public void setSorted(boolean sorted) {
        setSelected(sorted);
    }
}