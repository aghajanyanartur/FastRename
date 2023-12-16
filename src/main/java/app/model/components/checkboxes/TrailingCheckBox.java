package app.model.components.checkboxes;

import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;

public class TrailingCheckBox extends CheckBox {

    public TrailingCheckBox() {
        super("Name Pattern Trailing");
        setFont(Font.font("Arial", 16));
    }

    public boolean isTrailing() {
        return isSelected();
    }
}
