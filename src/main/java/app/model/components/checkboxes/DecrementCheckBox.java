package app.model.components.checkboxes;

import app.model.constraints.Constraints;
import javafx.scene.control.CheckBox;

public class DecrementCheckBox extends CheckBox {

    public DecrementCheckBox() {
        super("Decrement");
        setFont(Constraints.FONT);
    }

    public boolean isDecrement() {
        return isSelected();
    }

    public void setDecrement(boolean decrement) {
        setSelected(decrement);
    }
}
