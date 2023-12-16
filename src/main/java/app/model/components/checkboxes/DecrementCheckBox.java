package app.model.components.checkboxes;

import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;

public class DecrementCheckBox extends CheckBox {

    public DecrementCheckBox() {
        super("Decrement");
        setFont(Font.font("Arial", 16));
    }

    public boolean isDecrement() {
        return isSelected();
    }
}
